package ScalaTron

object TriSent {


  class ControlFunctionFactory {
    def create = new Bot().respond _
  }

  class Bot {
    var name: NameDisplay = User

    def respond(input: String): String = {
      val parseResult: (String, Map[String, String]) = CommandParser(input)
      val opcode: String = parseResult._1
      val paramMap: Map[String, String] = parseResult._2
      val lastDirStr:String = paramMap.getOrElse("lastDir", "0:1")
      val lastDir: XY = XY(lastDirStr)

      val viewString:String = paramMap("view")

      def inputAsXYOrElse(key: String, fallback: XY) = paramMap.get(key).map(s => XY(s)).getOrElse(fallback)

      def offsetToMaster = inputAsXYOrElse("master", XY.Zero)

      def display(time: Int): String = {
        if (time % 60 == 0) name = name.toggle
        name.getName()
      }

      val nameSeg = display(paramMap("time").toInt)
      val moveSeg = Mover(View(viewString), lastDir)
      val energy = EnergyLevel(paramMap("energy").toInt)

      def miniControlSys(): String = {
        val curOffset = offsetToMaster.negate
        val desiredOffset = paramMap("name") match {
          case "Alpha" => inputAsXYOrElse ("offset", XY(-1, 4))
          case "Beta" => inputAsXYOrElse ("offset", XY(1, -4))
          case "Gamma" => inputAsXYOrElse ("offset", XY(-4, -1))
          case "Delta" => inputAsXYOrElse ("offset", XY(4, 1))
        }

        val heading = ((desiredOffset - curOffset).signum)
        def enemyInRange: Boolean = viewString.exists(x => List('b','m','s').contains(x))
        if (enemyInRange) {
          val view = View(viewString)
          val nearB = view.offsetToNearest('b').getOrElse(XY(15,15))
          val nearM = view.offsetToNearest('m').getOrElse(XY(15,15))
          val nearS = view.offsetToNearest('s').getOrElse(XY(15,15))
          if (nearB.length < 4.0 || nearM.length < 4.0 || nearS.length < 4.0) "Explode(size=6)"
          else "Move(direction=" + heading + ")"
        }
        else "Move(direction=" + heading + ")"
      }

      def masterControlSys(): String = {
        val numSentinel = viewString.count(_ == 'S')
        val miniHeading = XY.Up
        def spawnBot(x: Int): String = (energy, x) match {
          case (MediumEnergy, 0) => "Spawn(direction=" + miniHeading + ",name=Alpha,energy=200,heading=" + miniHeading + ")"
          case (HighEnergy, 0) => "Spawn(direction=" + miniHeading + ",name=Alpha,energy=200,heading=" + miniHeading + ")"
          case (HighEnergy, 1) => "Spawn(direction=" + miniHeading + ",name=Beta,energy=200,heading=" + miniHeading + ")"
          case (MegaEnergy, 0) => "Spawn(direction=" + miniHeading + ",name=Alpha,energy=200,heading=" + miniHeading + ")"
          case (MegaEnergy, 1) => "Spawn(direction=" + miniHeading + ",name=Beta,energy=200,heading=" + miniHeading + ")"
          case (MegaEnergy, 2) => "Spawn(direction=" + miniHeading + ",name=Gamma,energy=200,heading=" + miniHeading + ")"
          case (MegaEnergy, 3) => "Spawn(direction=" + miniHeading + ",name=Delta,energy=200,heading=" + miniHeading + ")"
          case _ => ""
        }

        (energy, numSentinel) match {
          case (LowEnergy, _) => nameSeg + "|" + moveSeg
          case (MediumEnergy, 0) => // spawn 1
            name = Boom
            spawnBot(0) + "|" + moveSeg + "|" + name.getName()
          case (MediumEnergy, _) => moveSeg
          case (HighEnergy, 0) => // spawn 2
            name = Boom
            spawnBot(0) + "|" + moveSeg + "|" + name.getName()
          case (HighEnergy, 1) => // spawn 1 upper
            name = Boom
            spawnBot(1) + "|" + moveSeg + "|" + name.getName()
          case (HighEnergy, _) => moveSeg
          case (MegaEnergy, 0) => // spawn 3
            name = Boom
            spawnBot(0) + "|" + moveSeg + "|" + name.getName()
          case (MegaEnergy, 1) => // spawn 1 upper
            name = Boom
            spawnBot(1) + "|" + moveSeg + "|" + name.getName()
          case (MegaEnergy, 2) => // spawn 1 upper
            name = Boom
            spawnBot(2) + "|" + moveSeg + "|" + name.getName()
          case (MegaEnergy, 3) => // spawn 1 upper
            name = Boom
            spawnBot(3) + "|" + moveSeg + "|" + name.getName()
          case (MegaEnergy, _) => moveSeg
        }
      }

      opcode match {
        case "React" =>
          val gen = Generation(paramMap("generation").toInt)

          val goAction = (gen, energy) match {
            case (Master, MegaEnergy) => masterControlSys()// TODO high energy
            case (Master, HighEnergy) => masterControlSys()// TODO high energy
            case (Master, MediumEnergy) => masterControlSys()
            case (Master, LowEnergy) => nameSeg + "|" + moveSeg // moving the masterBot
            case (Gen1Mini, _) => miniControlSys()
          }
          goAction
        case _ => ""
      }

    }
  }

  case class View(cells: String) {
    def apply(index: Int) = cells.charAt(index)

    val size = math.sqrt(cells.length).toInt
    val center = XY(size / 2, size / 2)

    def indexFromAbsPos(absPos: XY) = absPos.x + absPos.y * size

    def absPosFromIndex(index: Int) = XY(index % size, index / size)

    def absPosFromRelPos(relPos: XY) = relPos + center

    def cellAtAbsPos(absPos: XY) = cells.charAt(indexFromAbsPos(absPos))

    def indexFromRelPos(relPos: XY) = indexFromAbsPos(absPosFromRelPos(relPos))

    def relPosFromAbsPos(absPos: XY) = absPos - center

    def relPosFromIndex(index: Int) = relPosFromAbsPos(absPosFromIndex(index))

    def cellAtRelPos(relPos: XY) = cells.charAt(indexFromRelPos(relPos))

    def offsetToNearest(c: Char) = {
      val relativePositions = cells.view.zipWithIndex.filter(_._1 == c)
        .map(p => relPosFromIndex(p._2))
      if (relativePositions.isEmpty) None
      else Some(relativePositions.minBy(_.length))
    }
  }


  sealed trait EnergyLevel
  object EnergyLevel{
    def apply(energy: Int): EnergyLevel = energy match {
      case x if(x <= 1500) => LowEnergy
      case x if(x > 1500 && x < 3500) => MediumEnergy
      case x if(x > 3500 && x < 5000)=> HighEnergy
      case _ => MegaEnergy
    }
  }
  final case object LowEnergy extends EnergyLevel
  final case object MediumEnergy extends EnergyLevel
  final case object HighEnergy extends EnergyLevel
  final case object MegaEnergy extends EnergyLevel

  sealed trait Generation
  object Generation {
    def apply(x:Int): Generation = x match {
      case 0 => Master
      case 1 => Gen1Mini
    }
  }
  final case object Master extends Generation
  final case object Gen1Mini extends Generation


  case class XY(x: Int, y: Int){

    override def toString = x + ":" + y

    def isNonZero = x!=0 || y!=0
    def isZero = x==0 && y==0
    def isNonNegative = x>=0 && y>=0

    def updateX(newX: Int) = XY(newX, y)
    def updateY(newY: Int) = XY(x, newY)
    def addToX(dx: Int) = XY(x+dx, y)
    def addToY(dy: Int) = XY(x, y+dy)

    def +(pos: XY) = XY(x+pos.x, y+pos.y)
    def -(pos: XY) = XY(x-pos.x, y-pos.y)
    def *(factor: Double) = XY((x*factor).intValue, (y*factor).intValue)

    def distanceTo(pos: XY) : Double = (this-pos).length
    def length : Double = math.sqrt(x*x + y*y)
    def signum = XY(x.sign, y.sign)
    def negate = XY(-x, -y)
    def negateX = XY(-x, y)
    def negateY = XY(x, -y)

  }

  object XY {
    def apply(s: String): XY = {
      val xy = s.split(':').map(_.toInt)
      XY(xy(0), xy(1))
    }

    val Zero = XY(0,0)
    val One = XY(1,1)
    val Right = XY(1, 0)
    val RightUp = XY(1, -1)
    val Up = XY(0,-1)
    val UpLeft = XY(-1, -1)
    val Left = XY(-1, 0)
    val LeftDown = XY(-1, 1)
    val Down =XY(0,1)
    val DownRight = XY( 1, 1)
  }

  object Mover {
    def apply(v: View, lastdir: XY): String = {
      v.offsetToNearest('B') match {
        case Some(offset) =>
          val unitOffset = offset.signum
          avoidDanger(v, unitOffset)._1
        case None => v.offsetToNearest('P') match {
          case Some(offset) =>
            val unitOffset = offset.signum
            avoidDanger(v, unitOffset)._1
          case None =>

            val result = avoidDanger(v, lastdir)
            result._1 + "|" + "Set(lastDir=" + result._2 + ")"
        }
      }
    }

    def avoidDanger(v: View, h: XY): (String, XY) = {
      val targetCel = v(v.indexFromRelPos(h))
      val isDangerous = targetCel == 'W' || targetCel == 'b'|| targetCel == 's' || targetCel == 'p' || targetCel == 'm'
      //        v(v.indexFromRelPos(h)) == 'p'
      if (isDangerous) {
        val newHeading = h match {
          case XY.Right => XY.RightUp
          case XY.RightUp => XY.Up
          case XY.Up => XY.UpLeft
          case XY.UpLeft => XY.Left
          case XY.Left => XY.LeftDown
          case XY.LeftDown => XY.Down
          case XY.Down => XY.DownRight
          case XY.DownRight => XY.Right
        }
        (avoidDanger(v, newHeading ))
      }
      else ("Move(direction=" + h + ")", h)
    }

  }

  object CommandParser {
    def apply(command: String) = {
      def splitParam(param: String) = {
        val segments = param.split('=')
        if (segments.length != 2)
          throw new IllegalStateException("invalid key/value pair: " + param)
        (segments(0), segments(1))
      }

      val segments = command.split('(')
      if (segments.length != 2)
        throw new IllegalStateException("invalid command: " + command)
      val params = segments(1).dropRight(1).split(',')
      val keyValuePairs = params.map(splitParam).toMap
      (segments(0), keyValuePairs)
    }
  }

  sealed trait NameDisplay {
    def getName(): String = this match {
      case Nick => "Status(text=m0n10dAlm16ht4!)"
      case User => "Status(text=Erle's Bot)"
      case Boom => "Status(text=Boom-Shaka-Scala!"
    }

    def toggle: NameDisplay = this match {
      case Nick => User
      case User => Nick
      case Boom => User
    }
  }

  final case object Nick extends NameDisplay
  final case object User extends NameDisplay
  final case object Boom extends NameDisplay






}
package ScalaTron

object SentSnipe {


  import util.Random

  class ControlFunctionFactory {
    def create = new Bot().respond _
  }

  class Bot {
    var name: NameDisplay = User
    val rnd = new Random()

    def respond(input: String): String = {
      val parseResult: (String, Map[String, String]) = CommandParser(input)
      val opcode: String = parseResult._1
      val paramMap: Map[String, String] = parseResult._2
      var lastDirStr:String = paramMap.getOrElse("lastDir", "0:1")
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


      def miniControlSys(): String = {
        //          val headingStr = paramMap("heading")
        //          val heading = XY(headingStr)
        val curOffset = offsetToMaster.negate
        val desiredOffset = inputAsXYOrElse("offset", XY(0, 4))
        val heading = ((desiredOffset - curOffset).signum)
        def zorgInRange: Boolean = viewString.contains('b')

        if (zorgInRange) {
          val view = View(viewString)
          val nearB = view.offsetToNearest('b').get
          val nearM = view.offsetToNearest('m').get
          val center = view.center
          val disNearestB = center.distanceTo(nearB)
          val disNearestM = center.distanceTo(nearM)
          if (disNearestB < 10.0 || disNearestM < 10) "Explode(size=10)"
          else "Move(direction=" + heading + ")"
        }
        else "Move(direction=" + heading + ")"
      }

      def masterControlSys(): String = {
        val hasSentinel = viewString.contains('S')
        val miniHeading = XY.Up
        val spawnBot: String = "Spawn(direction=" + miniHeading + ",energy=300,heading=" + miniHeading + ")"
        if (hasSentinel) moveSeg
        else spawnBot + "|" + moveSeg
      }


      opcode match {
        case "React" =>
          val gen = Generation(paramMap("generation").toInt)

          val energy = EnergyLevel(paramMap("energy").toInt)

          val goAction = (gen, energy) match {
            case (Master, HighEnergy) => masterControlSys()// TODO high energy
            case (Master, MediumEnergy) => masterControlSys()
            case (Master, LowEnergy) => nameSeg + "|" + moveSeg // moving the masterBot
            case (Gen1Mini, _) => miniControlSys()
          }
          goAction
//          "Set(last=00)"
//          var temp = paramMap.getOrElse("last", "00") + 1
//          "Set(lastDir=" + lastDirStr + ")"
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
      case x if(x <= 2000) => LowEnergy
      case x if(x > 2000 && x < 3000) => MediumEnergy
      case _ => HighEnergy
    }
  }
  final case object LowEnergy extends EnergyLevel
  final case object MediumEnergy extends EnergyLevel
  final case object HighEnergy extends EnergyLevel

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
    def random(rnd: Random) = XY(rnd.nextInt(3)-1, rnd.nextInt(3)-1)

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
      val rnd = new Random()
      def dx = rnd.nextInt(3)-1
      def dy = rnd.nextInt(3)-1

      v.offsetToNearest('P') match {
        case Some(offset) =>
          val unitOffset = offset.signum
          avoidDanger(v, unitOffset)._1
//          "Move(direction=" + unitOffset + ")"
        case None => v.offsetToNearest('B') match {
        case Some(offset) =>
        val unitOffset = offset.signum
          avoidDanger(v, unitOffset)._1
//        "Move(direction=" + unitOffset + ")"
        case None =>

//          temp._1 //+ "|" + "Set(lastDir=" + lastDir.x.toString + lastDir.y.toString + ")"
          val result = avoidDanger(v, lastdir)
//          val temp = result._2
//          val lastDirStr:String = result._2.x.toString + result._2.y.toString
          result._1 + "|" + "Set(lastDir=" + result._2 + ")"
//          "Move(direction=" + dx + ":" + dy + ")"
        }
      }
    }

    def avoidDanger(v: View, h: XY): (String, XY) = {
      val targetCel = v(v.indexFromRelPos(h))
      val isDangerous = targetCel == 'W' || targetCel == 'b'||
          v(v.indexFromRelPos(h)) == 'p'
      if (isDangerous) {
        val newHeading = h match {
          case XY(1, 0) => XY(1, -1)
          case XY(1, -1) => XY(0, -1)
          case XY(0, -1) => XY(-1, -1)
          case XY(-1, -1) => XY(-1,0)
          case XY(-1, 0) => XY(-1, 1)
          case XY(-1, 1) => XY(0, 1)
          case XY(0, 1) => XY(1, 1)
          case XY(1, 1) => XY(1, 0)
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
    }

    def toggle: NameDisplay = this match {
      case Nick => User
      case User => Nick
    }
  }

  final case object Nick extends NameDisplay
  final case object User extends NameDisplay


  def main(args: Array[String]): Unit = {
    val testbot = new ControlFunctionFactory
    val botStatus = testbot.create("test")
    println(botStatus)
  }


}

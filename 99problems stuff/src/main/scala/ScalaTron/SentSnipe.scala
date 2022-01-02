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

      def inputAsXYOrElse(key: String, fallback: XY) = paramMap.get(key).map(s => XY(s)).getOrElse(fallback)

      def offsetToMaster = inputAsXYOrElse("master", XY.Zero)

      def miniControlSys(): String = {
        //          val headingStr = paramMap("heading")
        //          val heading = XY(headingStr)
        val curOffset = offsetToMaster.negate
        val desiredOffset = inputAsXYOrElse("offset", XY(0, 4))
        val heading = ((desiredOffset - curOffset).signum)
        "Move(direction=" + heading + ")"
      }

      def masterControlSys(): String = ???

      def display(time: Int): String = {
        if (time % 60 == 0) name = name.toggle
        name.getName()
      }

      opcode match {
        case "React" =>
          val nameSeg = display(paramMap("time").toInt)
          val moveSeg = Mover()

          val gen = Generation(paramMap("generation").toInt)

          val energy = EnergyLevel(paramMap("energy").toInt)

          val goAction = (gen, energy) match {
            case (Master, MediumEnergy) =>
              val miniHeading = XY.Up
              val spawnBot: String = "Spawn(direction=" + miniHeading + ",energy=300,heading=" + miniHeading + ")"
              spawnBot + "|" + moveSeg
            case (Master, LowEnergy) => nameSeg + "|" + moveSeg // moving the masterBot
            case (Gen1Mini, _) => miniControlSys()
          }
          goAction
        case _ => ""
      }

    }
  }

  sealed trait EnergyLevel
  object EnergyLevel{
    def apply(energy: Int): EnergyLevel = energy match {
      case x if(x <= 400) => LowEnergy
      case x if(x > 400 && x < 1200) => MediumEnergy
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
    def apply(): String = {
      val rnd = new Random()
      val dx = rnd.nextInt(3)-1
      val dy = rnd.nextInt(3)-1
      "Move(direction=" + dx + ":" + dy + ")"
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

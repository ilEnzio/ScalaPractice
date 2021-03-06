package ScalaTron
/*
Plan

Move
Eat/Gather energy
Defend
Display


 */
object FDVenom {


  import util.Random

  class ControlFunctionFactory {
    def create = new Bot().respond _
  }

  class Bot {
    var name: NameDisplay = User
    val rnd = new Random()

    def respond(input: String): String = {
      val parseResult = CommandParser(input)
      val opcode = parseResult._1
      val paramMap = parseResult._2

      if (opcode == "React") {
        val nameSeg = display(paramMap("time").toInt)
        val moveSeg = Mover()
        val generation = paramMap("generation").toInt
        if (generation == 0) {
          if (paramMap("energy").toInt >= 100 && rnd.nextDouble() < 0.05) {
            val xy = XY.random(rnd)
            val direction = xy.x + ":" + xy.y
            val spawnBot: String = "Spawn(direction=" + direction + ",energy=100,heading=" + direction + ")"
            spawnBot
          } else nameSeg + "|" + moveSeg
        } else {
          val heading = paramMap("heading")
          "Move(direction=" + heading + ")"
        }
      } else ""
    }
    def display(time: Int): String = {
      if (time % 60 == 0) name = name.toggle
      name.getName()
    }
  }

  case class XY(x: Int, y: Int){
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
    def signum = XY(x.signum, y.signum)
    def negate = XY(-x, -y)
    def negateX = XY(-x, y)
    def negateY = XY(x, -y)
  }

  object XY {
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

}

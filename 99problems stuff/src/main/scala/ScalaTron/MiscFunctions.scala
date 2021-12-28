package ScalaTron

import ScalaTron.ReferenceBot.ControlFunction

object MiscFunctions {

  //  def stroll() = {
  //    val dir: List[Int] = [1, ]


  class ControlFunctionFactory {
    def create = new Bot().respond _
  }

  class Bot {
    var name: NameDisplay = Nick
    def respond(input: String) = {
      val test = Stats().increment
      if (test % 100 == 0)  name = name.toggle
      else name
      name.getName()
    }
  }

  class Stats {
    def increment =  Stats.getCount()
    def getStatus = "Status(text=Erle's Bot)"
    def getNick = "Status(text=m0n10dAlm16ht33!)"
  }
  object Stats{
    private var counter:Int = 0
    def apply() = {
      counter += 1
      new Stats }
    def getCount() = counter
  }

  sealed trait NameDisplay {
    def getName(): String

    def toggle: NameDisplay = this match {
      case Nick => User
      case User => Nick
    }
  }

  final case object Nick extends NameDisplay{
    override def getName() = "Status(text=m0n10dAlm16ht33!)"
  }

  final case object User extends NameDisplay {
    override def getName(): String = "Status(text=Erle's Bot)"
  }


object CommandParser{
    def apply(command: String) = {
      def splitParam(param: String) = {
        val segments = param.split('=')
        if( segments.length != 2)
          throw new IllegalStateException(
            "invalid key/value pair: " + param)
        (segments(0), segments(1))
      }

      val segments = command.split('(')
      if (segments.length != 2 )
        throw new IllegalStateException(
          "invalid command: " + command )

      val params = segments(1).dropRight(1).split(',')
      val keyValuePairs = params.map( splitParam ).toMap
      (segments(0), keyValuePairs)
    }
  }

//  class ControlFunction(val initState: Stats = Stats(0)) {

    //  def respond(in: String, s: Stats) = {
    //    val state = Stats(s.num)
    //    val output = "Status(text=" + state.num + ")"
    //    output
    //  }

    //  def respond(input: String) = {
    //    val tokens = input.split('(')
    //    if(tokens(0)=="React") {"Move(direction=1:0)"}
    //    else { ""}
    //  }
    //def respond(input: String) = {
    //  val tokens = input.split('(')
    //  val opcode = tokens(0)
    //  if(opcode=="React") {
    //    val rest = tokens(1).dropRight(1)
    //    val params = rest.split(',')
    //    val strPairs = params.map(s => s.split('='))
    //    val kvPairs = strPairs.map(a => (a(0),a(1)))
    //    val paramMap = kvPairs.toMap
    //    val energy = paramMap("energy").toInt
    //  "Status(text=Energy:" + energy + ")"}
    //  else { "" }
    //}
//    def respond(input: String) = {
//      val parseResult = parse(input)
//      val opcode = parseResult._1
//      val paramMap = parseResult._2
//      if (opcode == "React") {
//        "Status(text=Energy:" + paramMap("energy") + ")"
//      } else {
//        ""
//      }
//    }
//
//    def parse(command: String) = {
//      def splitParam(param: String) = {
//        val segments = param.split('=')
//        if (segments.length != 2)
//          throw new IllegalStateException("invalid key/value pair: " + param)
//        (segments(0), segments(1))
//      }
//
//      val segments = command.split('(')
//      if (segments.length != 2)
//        throw new IllegalStateException("invalid command: " + command)
//      val params = segments(1).dropRight(1).split(',')
//      val keyValuePairs = params.map(splitParam).toMap
//      (segments(0), keyValuePairs)
//
//
//    }
//
//  }

  def main(args: Array[String]): Unit = {
    val testbot = new ControlFunctionFactory
    val botStatus = testbot.create("test")
    println(botStatus)
  }
}
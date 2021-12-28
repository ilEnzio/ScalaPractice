package ScalaTron

import ScalaTron.ReferenceBot.ControlFunction


object MiscFunctions {


  class ControlFunctionFactory {
    def create = new Bot().respond _
  }

  class Bot {
    var name: NameDisplay = User

    def respond(input: String) = {
      val parseResult = CommandParser(input)
      val opcode = parseResult._1
      val paramMap = parseResult._2

      if (opcode=="React") {
        val nameSeg = display(paramMap("time").toInt)
        nameSeg
      }
      else ""
    }

    def display(time: Int): String = {
      if (time % 30 == 0) name = name.toggle
      name.getName()
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

  final case object Nick extends NameDisplay{}
  final case object User extends NameDisplay{}


//
//object CommandParser{
//    def apply(command: String) = {
//      def splitParam(param: String) = {
//        val segments = param.split('=')
//        if( segments.length != 2)
//          throw new IllegalStateException(
//            "invalid key/value pair: " + param)
//        (segments(0), segments(1))
//      }
//
//      val segments = command.split('(')
//      if (segments.length != 2 )
//        throw new IllegalStateException(
//          "invalid command: " + command )
//
//      val params = segments(1).dropRight(1).split(',')
//      val keyValuePairs = params.map( splitParam ).toMap
//      (segments(0), keyValuePairs)
//    }
//  }

//  class ControlFunction(val initState: Stats = Stats(0)) {

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
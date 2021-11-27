package EssentialScalaBookEx.Ch04

import sun.tools.tree.GreaterExpression

object StopOnDime {


  // 4.4.4.1

  sealed trait TrafficLight{
    def next: TrafficLight
    def next_v2: TrafficLight = this match{
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }

  case object Red extends TrafficLight {
    override def next: TrafficLight = Green
  }
  case object Green extends TrafficLight{
    override def next: TrafficLight = Yellow
  }
  case object Yellow extends TrafficLight{
    override def next: TrafficLight = Red
  }

  object LightToggle {
    def apply(current: TrafficLight): TrafficLight = current match {
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }

  class Person1(name: String){
    val nickName: String = name  + " YaaTaa!! Granger"}

  class Person2(val name: String){
    val nicName: String = name + " YaaTaa!! Granger"}

  class SuspectPerson(){
    var name: String = "Anon"
    val problemNick: String = name + " YaaTaa!! Granger"
  }

  def main(args: Array[String]): Unit = {

    val erle = new Person1("Erle")
//    val getName = erle.name // won't compile
    val getNick = erle.nickName // No problem

    val otherErle = new Person2("Erle")
    val getOtherName = otherErle.name // no problem
    val getOtherNick = erle.nickName // No problem

    val who = new SuspectPerson
    val getWhoName = who.name
    who.name = "Erle"
    val getWhoNick = who.problemNick // compiles but unexpected result

    println(getWhoNick)
  }
}

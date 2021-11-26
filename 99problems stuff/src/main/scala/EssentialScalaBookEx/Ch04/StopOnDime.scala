package EssentialScalaBookEx.Ch04

object StopOnDime {


  // 4.4.4.1

  sealed trait TrafficLight
  case object Red extends TrafficLight
  case object Green extends TrafficLight
  case object Yellow extends TrafficLight

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

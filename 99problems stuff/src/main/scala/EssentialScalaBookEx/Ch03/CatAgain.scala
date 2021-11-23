package EssentialScalaBookEx.Ch03

object CatAgain {

  case class Cat (name: String, colour: String, food: String) {
  }

  object ChipShop {
//    def willServe(c: Cat): Boolean = c.food.toLowerCase() == "chips"
//    def willServe(c: Cat): Boolean = c.food.toLowerCase match {
//      case "chips" => true
//      case _ => false

    def willServe(c: Cat): Boolean = c match {
      case Cat(_, _, "Chips") => true
      case Cat(_, _, _) => false
    }
  }

  def main(args: Array[String]): Unit = {

    val os = Cat("Oswald", "Black", "Milk")
    val henderson = Cat("Henderson", "Ginger", "Chips")
    val quentin = Cat("Quentin", "Tabby and white", "Curry")

    println(os)
    println(henderson)
    println(quentin)

    assert(ChipShop.willServe(os) == false)
    assert(ChipShop.willServe(henderson) == true)



  }
}

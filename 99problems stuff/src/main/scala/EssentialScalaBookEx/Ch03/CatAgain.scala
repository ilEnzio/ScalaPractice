package EssentialScalaBookEx.Ch03

object CatAgain {

  class Cat (name: String, colour: String, food: String) {
    override def toString: String = s"$name, $colour, $food"
  }


  def main(args: Array[String]): Unit = {

    val os = new Cat("Oswald", "Black", "Milk")
    val henderson = new Cat("Henderson", "Ginger", "Chips")
    val quentin = new Cat("Quentin", "Tabby and white", "Curry")

    println(os)
    println(henderson)
    println(quentin)
    


  }
}

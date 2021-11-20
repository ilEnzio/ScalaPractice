package EssentialScalaBookEx.Ch02

object CatO {

  trait Cat{
    val name: String
    val colour: String
    val food: String

    override def toString: String = s"$name, likes: $colour and $food"
  }
  object Oswald extends Cat {
    val name = "Oswald"
    val colour = "Black"
    val food = "Milk"

  }

  object Henderson extends Cat{
    val name = "Henderson"
    val colour = "Ginger"
    val food = "Chips"
  }

  object Quentin extends Cat{
    val name = "Quentin"
    val colour = "Tabby and white"
    val food = "Curry"
  }

  def main(args: Array[String]): Unit = {

    println(Oswald)


  }

}
package ScalaWithCats.Ch01

object PrintableExercise {

  sealed trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val stringPrintable: Printable[String] = new Printable[String] {
      def format(value: String) = value
    }

    implicit val intPrintable: Printable[Int] = new Printable[Int] {
      def format(value: Int): String = value.toString
    }

    implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
      def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."

      // book solution
//      def format(cat: Cat) = {
//        val name  = Printable.format(cat.name)
//        val age   = Printable.format(cat.age)
//        val color = Printable.format(cat.color)
//        s"$name is a $age year-old $color cat."
    }
  }


  object Printable {
    def format[A](value: A)(implicit printable: Printable[A]): String =
      printable.format(value)

    def print[A](value: A)(implicit printable: Printable[A]): Unit =
      println(format(value))
  }


  object PrintableSyntax {
    implicit class PrintableOps[A](value: A) {
      def format(implicit printable: Printable[A]): String =
        printable.format(value)

      def print(implicit  printable: Printable[A]): Unit =
        println(format(printable))
    }
  }

  final case class Cat(name: String, age: Int, color: String){
//    override def toString: String = s"${name} is a ${this.age} year-old ${this.color} cat."
  }

  def main(args: Array[String]): Unit = {
    import PrintableInstances._

    val erlrCat = Cat("Erle", 50, "Black")
    val lilSnookie = Cat("lil snookie", 30, "coco")

//    println(lilSnookie.name, lilSnookie.age, lilSnookie.color)
//    println(lilSnookie)

//    Printable.print(erlrCat)
//    Printable.print(lilSnookie) // invisible

    import PrintableSyntax._
//    erlrCat.print
      lilSnookie.print
  }
}
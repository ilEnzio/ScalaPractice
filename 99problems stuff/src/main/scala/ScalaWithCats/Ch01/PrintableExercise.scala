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
  }


  object Printable {
    def format[A](value: A)(implicit printable: Printable[A]): String =
      printable.format(value)

    def print[A](value: A)(implicit printable: Printable[A]): Unit =
      println(format(value))
  }

  def main(args: Array[String]): Unit = {

  }
}
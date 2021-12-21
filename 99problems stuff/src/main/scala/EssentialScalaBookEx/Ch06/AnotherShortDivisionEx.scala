package EssentialScalaBookEx.Ch06

object AnotherShortDivisionEx {

  /**
  Write a method divide that accepts two Int parameters and divides one by the other.
    Use Option to avoid exceptions when the denominator is 0.

   */

  def shortDiv(a: Int, b: Int): Option[Int] =
    if (b == 0) None else Some(a/b)

  def divideOptions(a: Option[Int], b: Option[Int]): Option[Int] = {
    for {
      aa <- a
      bb <- b
      c <- shortDiv(aa, bb)
    } yield { c }
  }


  def main(args: Array[String]): Unit = {

    println(shortDiv(2, 0))
    println(shortDiv(10, 2))
    println(shortDiv(0, 2))



  }

}
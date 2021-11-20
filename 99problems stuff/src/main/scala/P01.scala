object P01 {

  //  P01 (*) Find the last element of a list.
  //  Example:
  //
  //    scala> last(List(1, 1, 2, 3, 5, 8))
  //
  //  res0: Option[Int] = Some(8)
  //
  //  scala> last(List.empty[Int])
  //
  //  res1: Option[Int] = None

  def findLast[A](list: List[A]): Option[A] = list match {
    case x if (x == List.empty) => None
    case List(x) => Some(x)
    case x :: xs => findLast(xs)
  }



  def main(args: Array[String]): Unit = {

    val test1 = List(1, 1, 2, 3, 5, 8)
    val testEmpty = List.empty
    val test1ele = List(28)


    println(s"test1 - ${findLast(test1)}")
    println(s"testEmpty - ${findLast(testEmpty)}")
    println(s"test1ele - ${findLast(test1ele)}")

  }

}

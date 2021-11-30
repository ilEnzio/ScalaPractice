import scala.math.abs

object P19 {

  //  P19 (**) Rotate a list N places to the left.
  //    Examples:
  //
  //    scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //
  //  res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
  //
  //  scala> rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //
  //  res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)

  // TODO: negative numbers; works but...
  // came up with a modulous solution, feels janky... I go through the collection a bunch of times
  def rotate[A](n: Int, l: List[A]): List[A] = {
    // deal with negatives
    val w = if (n < 0) abs((n % l.length) + l.length ) else n

    // plan: get a tuple of two lists with the prospective 'slices', then
    // combine the lists in the correct order

    // add indexes; fold to tuple of Lists, swapping the order in the tuple, then
    // reverse each list, and concatenate them.

    val t = l.zipWithIndex.foldLeft(List[A](), List[A]()){ case (state, value: (A, Int)) =>
      value._2 match {
        case x if (x < w ) => (state._1, value._1 :: state._2)
        case _ => (List(value._1) ++ state._1, state._2)
      }
    }
//    if (n < 0) println(abs((n % 11) + 11 )) else println(n)
    t._1.reverse ++ t._2.reverse
  }

  def main(args: Array[String]): Unit = {

    val testList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

    println(rotate(3, testList))
    println(rotate(-2, testList))
    println(rotate(-24, testList))
    println(rotate(9, testList))
  }
}
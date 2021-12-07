

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

  // came up with a modulous solution, feels janky... I go through the collection a bunch of times
  def rotate[A](n: Int, l: List[A]): List[A] = {
    // deal with negatives
    val lLen = l.length
    val w = if (n < 0) abs((n % lLen) + lLen) else n // give a comment here

    // plan: get a tuple of two lists with the prospective 'slices', then
    // combine the lists in the correct order

    // add indexes; fold to tuple of Lists, swapping the order in the tuple, then
    // reverse each list, and concatenate them.

    // TIL - use List.empty rather than List()
    ///            - if all you're doing is checking the condition, you don't need a pattern match, in other
    //               words I don't need to deconstruct.
    //              - with tuples, consider deconstructing and naming the parts for readability
    //              - think about how you can reuse the code , and comment stuff with maths!
    val t = l.zipWithIndex.foldLeft(List.empty[A], List.empty[A]){ case ((unRot, rotat), (value, idx)) =>
      if (idx < w ) (unRot, value :: rotat)
      else (value :: unRot, rotat)
    }

    //    if (n < 0) println(abs((n % 11) + 11 )) else println(n)
    t._1.reverse ++ t._2.reverse
  }

  def main(args: Array[String]): Unit = {

    val testList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val testEmpty = List()
    println(rotate(3, testList))
    println(rotate(-2, testList))
    println(rotate(-24, testList))
    println(rotate(9, testList))
    println(rotate(6, testEmpty))
  }
}
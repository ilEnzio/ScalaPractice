package EssentialScalaBookEx.Ch05

import EssentialScalaBookEx.Ch05.MaybeMistake.Maybe
import EssentialScalaBookEx.Ch05.MaybeMistake.Full
import EssentialScalaBookEx.Ch05.MaybeMistake.Empty

object SequencingComputations {

  /**
   * We’re going to use Scala’s builtin List class for this exercise as it has a flatMap method.

Given this list

val list = List(1, 2, 3)
return a List[Int] containing both all the elements and their negation.
  Order is not important. Hint: Given an element create a list containing it and its negation.
   */

  val list = List(1, 2, 3)

  /**
   *Given this list

  val list: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
  return a List[Maybe[Int]] containing None for the odd elements.
  Hint: If x % 2 == 0 then x is even.
   */

  val list2: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))

  def main(args: Array[String]): Unit = {

    println(list.flatMap{ x => List(x, -x)})

    println(list2.map{case MaybeMistake.Full(x) => if ( x % 2 == 0) Full(x) else Empty()})
    println(list2.map{(x: MaybeMistake.Maybe[Int]) => x.flatMap[Int]{ x => if ( x % 2 == 0) Full(x) else Empty()}})

//    list.map(maybe => maybe.flatMap[Int] { x => if (x % 2 == 0) Full(x) else Empty() })
  }

}

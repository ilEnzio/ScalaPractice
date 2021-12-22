package Probs

import scala.annotation.tailrec

object P03 {

  //  P03 (*) Find the Kth element of a list.
  //  By convention, the first element in the list is element 0.
  //
  //  Example:
  //
  //    scala> nth(2, List(1, 1, 2, 3, 5, 8))
  //
  //  res0: Option[Int] = Some(2)

  def getKth[A](k: Int, l: List[A]): Option[A] = {
    val validInput = k >= 0 && k < l.length - 1 // if I run this on the something other than a list, may throw exception
    if (validInput) Some(l(k)) else None
  }

  def getKth_v2[A](k: Int, l: List[A]): Option[A] = {
    val validInput = k >= 0 && k < l.length - 1

    def doGetKth(l: List[A], acc: Int = 0): Option[A] =
      l match {
        case Nil => None
        case h :: _ if (k == acc) => Some(h)
        case _ :: t => doGetKth(t, acc + 1)
      }

    if (validInput) doGetKth(l) else None
  }

  // best solution
  @tailrec
  def getKth_v3[A](k: Int, l: List[A]): Option[A] = {
    l match {
      case _ if (k < 0) => None
      case Nil => None
      case list if (k == 0) => list.headOption
      case _ :: t => getKth_v3(k - 1, t) //
    }
  }

  def main(args: Array[String]): Unit = {

    val testList = List(1, 1, 2, 3, 5, 8)
    val testEmpty = List()
    val test1ele = List(1)
    println(getKth(2, testList))
    println(getKth(2, testEmpty))
    println(getKth(2, test1ele))
    println(getKth(-1, testList))

    // test for v 2
    assert(getKth_v2(2, testList) == Some(2))
    assert(getKth_v2(2, testEmpty) == None)
    assert(getKth_v2(2, test1ele) == None)
    assert(getKth_v2(-1, testList) == None)

    assert(getKth_v3(2, testList) == Some(2))
    assert(getKth_v3(2, testEmpty) == None)
    assert(getKth_v3(2, test1ele) == None)
    assert(getKth_v3(-1, testList) == None)
  }

}

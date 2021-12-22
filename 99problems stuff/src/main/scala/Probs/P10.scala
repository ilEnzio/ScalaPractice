package Probs

import Probs.P09.pack

object P10 {
  //
  //  P10 (*) Run-length encoding of a list.
  //    Use the result of problem P09 to implement the so-called run-length encoding data compression method. Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates of the element E.
  //
  //  Example:
  //
  //    scala> encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //
  //  res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
  //
  val symPacker: List[Symbol] => List[List[Symbol]] = pack[Symbol]

  def encode[A](l: List[A], enc: List[A] => List[List[A]]): List[(Int, A)] = {
    enc(l).map(x => (x.length, x.head))
  }


  def main(args: Array[String]): Unit = {

    val testList01 = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    println(encode(testList01, symPacker))


  }

}

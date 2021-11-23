object P12 {

//  P12 (**) Decode a run-length encoded list.
//    Given a run-length code list generated as specified in problem P10, construct its uncompressed version. Example:
//
//    scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
//
//  res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)



  def decode[A](encL: List[(Int,A)]): List[A] = {

    def duplicate[A](n: Int, ele: A, acc: List[A] = Nil): List[A] = {
      if (n == 0) acc else duplicate(n-1, ele, ele :: acc)
    }

    encL.map(x => duplicate(x._1, x._2)).flatMap(x => x)

  }

  def main(args: Array[String]): Unit = {

    val test01 = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
    println(decode(test01))
  }
}

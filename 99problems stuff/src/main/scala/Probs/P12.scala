package Probs

object P12 {

  //  P12 (**) Decode a run-length encoded list.
  //    Given a run-length code list generated as specified in problem P10, construct its uncompressed version. Example:
  //
  //    scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
  //
  //  res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)


  def decode[A](encL: List[(Int, A)]): List[A] = {

    def duplicate[A](n: Int, ele: A, acc: List[A] = Nil): List[A] = {
      if (n == 0) acc else duplicate(n - 1, ele, ele :: acc)
    }
    //    encL.map(x => duplicate(x._1, x._2)).flatMap(x => x)
    //    encL.map(x => duplicate(x._1, x._2)).flatten
    encL.flatMap { case (x, y) => duplicate(x, y) }
    //    encL.flatMap(duplicate.tupled _) TODO
    // why doesn't the tuple work
    //    encL.map((a: Int, b: A) => duplicate(a, b, Nil).flatMap(x: List[A] => x)
  }

  //  use fold
  def decode_v2[A](encL: List[(Int, A)]): List[A] = {

    def duplicate[A](n: Int, ele: A, acc: List[A] = Nil): List[A] = {
      if (n == 0) acc else duplicate(n - 1, ele, ele :: acc)
    }

    encL.foldRight(List[A]()) { case (e, s) =>
      val newL = duplicate(e._1, e._2)
      newL ++ s
    }
  }

  //  use for comprehension
  def decode_v3[A](l: List[(Int, A)]): List[A] = {
    def duplicate[A](n: Int, ele: A, acc: List[A] = Nil): List[A] = {
      if (n == 0) acc else duplicate(n - 1, ele, ele :: acc)
    }

    for {
      a <- l
      out <- duplicate(a._1, a._2)
    } yield {
      out
    }
  }

  def main(args: Array[String]): Unit = {

    val test01 = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
    //    println(decode(test01))

    println(decode_v3(test01))
  }
}

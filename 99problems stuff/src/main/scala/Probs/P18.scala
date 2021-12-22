package Probs

object P18 {
  //
  //  P18 (**) Extract a slice from a list.
  //    Given two indices, I and K, the slice is the list containing the elements from and including the Ith element up to but not including the Kth element of the original list. Start counting the elements with 0.
  //
  //  Example:
  //
  //    scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //
  //  res0: List[Symbol] = List('d, 'e, 'f, 'g)


  def slice[A](st: Int, e: Int, l: List[A]): List[A] = {
    // add indexes; filter out all but the range; map indexes away
    l.zipWithIndex.filter(x => (x._2 >= st && x._2 < e)).map { case (a, _) => a }
  }

  def slice_v2[A](st: Int, end: Int, l: List[A]): List[A] = {
    l.foldRight(List.empty[A], l.length - 1) { case (v, (s, idx)) =>
      if (idx >= st && idx < end) (v :: s, idx - 1)
      else (s, idx - 1)
    }._1
  }

  def main(args: Array[String]): Unit = {

    val testList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

    println(slice(3, 7, testList))
    println(slice_v2(3, 7, testList))
  }
}

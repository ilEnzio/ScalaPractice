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

  // try zip with index and maybe filter


  def slice[A](st: Int, e: Int, l: List[A]): List[A] = {
    l.zipWithIndex.filter(x => (x._2 >= st && x._2 < e)).map { case (a, _) => a }
  }

  def main(args: Array[String]): Unit = {

    val testList =  List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    println(slice(3, 7, testList))

  }
}
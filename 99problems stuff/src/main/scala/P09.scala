object P09 {

//  P09 (**) Pack consecutive duplicates of list elements into sublists.
//    If a list contains repeated elements they should be placed in separate sublists.
//
//    Example:
//
//    scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//
//  res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
//

  def pack[A](l: List[A]): List[List[A]] = {
    def doPack(list: List[A], subAcc: List[A], acc: List[List[A]]): List[List[A]] = list match {
      case Nil => acc
      case h :: s :: t if (h == s) => doPack(h::t, s :: subAcc, acc)
      case h :: t => doPack(t, Nil, (h::subAcc) :: acc)
    }
    doPack(l, Nil, Nil).reverse
  }

  def main(args: Array[String]): Unit = {

    val test01 = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    println(pack(test01))
  }

}

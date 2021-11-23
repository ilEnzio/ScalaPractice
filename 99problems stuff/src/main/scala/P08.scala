object P08 {
//  P08 (**) Eliminate consecutive duplicates of list elements.
//    If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
//
//    Example:
//
//    scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//
//  res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)


  def compress[A](l: List[A]): List[A] = {
    def doCompress(list: List[A], acc: List[A] = Nil): List [A] = list match {
      case Nil => acc
      case h :: s :: t  if (h == s) => doCompress(h :: t, acc)
      case h :: t  => doCompress(t, acc ++ List(h))
    }
    doCompress(l)
  }

  def main(args: Array[String]): Unit = {


    val test01 = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    println(compress(test01))
  }
}

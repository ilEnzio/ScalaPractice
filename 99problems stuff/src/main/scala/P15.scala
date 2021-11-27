object P015 {
  //  P15 (**) Duplicate the elements of a list a given number of times.
  //  Example:
  //
  //    scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
  //
  //  res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)

  def duplicateN[A](n: Int, l: List[A]): List[A] = {
    def dupe[A](x: Int, ele: A, acc: List[A] = Nil): List[A] = {
      if (x == 0) acc else dupe(x-1, ele, ele :: acc)
    }
    l.map(a => dupe(n, a) ).flatMap(x => x)
  }

  def duplicateN_v2[A](n: Int, l: List[A]): List[A] = {

    def dupe[A](x: Int, ele: A, acc: List[A] = Nil): List[A] = {
      if (x == 0) acc else dupe(x-1, ele, ele :: acc)
    }

    //    def folder(state: List[A], value: A): List[A] = {
    //      dupe(n, value, state)
    //    }
    //    l.foldLeft(List[A]())(folder).reverse
    // use lambda
    l.foldLeft(List[A]()){ case (state, value) => dupe(n, value, state)}.reverse
  }

  def main(args: Array[String]): Unit = {

    val testList01 = List('a, 'b, 'c, 'c, 'd)

    println(duplicateN(3, testList01))
    println(duplicateN_v2(3, testList01))

  }
}

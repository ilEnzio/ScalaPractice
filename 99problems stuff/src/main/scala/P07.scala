object P07 {
  //
  //  P07 (**) Flatten a nested list structure.
  //  Example:
  //
  //    scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  //
  //  res0: List[Any] = List(1, 1, 2, 3, 5, 8)


  def flatten(nl: List[Any]): List[Any] = {
    def doflat(l: List[Any], acc: List[Any]): List[Any] = {
      println(s"$l ------ $acc")
      l match {
        case Nil => acc
        case (h: List[_]) :: t => doflat(h ++ t, acc) // this step is tricky!!
        case h :: t => doflat(t, acc ++ List(h))
      }
    }
    doflat(nl, Nil)
  }

  //  TODO EXERCISE - def flatten([A]ls: List[List[A]]) = List[A] = ???

  def otherFlatten[A](ls: List[List[A]]) : List[A] = {
    def doFlatten(l: List[List[A]], acc: List[A]): List[A] = l match {
      case Nil => acc
      case h :: t => doFlatten(t, acc ++ h)
    }
    doFlatten(ls, Nil)
  }

  def main(args: Array[String]): Unit = {

    val testNest = List(List(1, 1), 2, List(3, List(5, 8)))

    val testOther = List(List(112,2), List(5,6,7), List(89, 11, 101))

    println(testNest)
    println(flatten(testNest))
    println(otherFlatten(testOther))



  }
}
package Probs

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

  // acc, collection, folder, value
  // (A, B) => B
  def flatten_v2(nl: List[Any]): List[Any] = {
    //    def folder(state: List[Any], value: Any): List[Any] = value match {
    //      case x: List[_]  => x ++ state
    //      case x => List(x) ++ state
    //
    //    }
    //    nl.foldLeft(List[Any]())(folder).reverse
    /// TODO - Review THIS!
    /// If there is only one case, why do I have to match it?
    nl.foldLeft(List[Any]()) { case (state, value) =>
      value match {
        case h: List[_] => flatten_v2(h) ++ state
        case x => x :: state
      }
    }.reverse
  }

  //  EXERCISE - def flatten([A]ls: List[List[A]]) = List[A] = ???

  def otherFlatten[A](ls: List[List[A]]): List[A] = {
    def doFlatten(l: List[List[A]], acc: List[A]): List[A] = l match {
      case Nil => acc
      case h :: t => doFlatten(t, acc ++ h)
    }

    doFlatten(ls, Nil)
  }

  def otherFlatten_v2[A](ls: List[List[A]]): List[A] = {
    def folder(state: List[A], value: List[A]): List[A] = {
      state ++ value
    }

    ls.foldLeft(List[A]())(folder)
  }

  def main(args: Array[String]): Unit = {

    val testNest = List(List(1, 1), 2, List(3, List(5, 8)))

    val testOther = List(List(112, 2), List(5, 6, 7), List(89, 11, 101))

    println(testNest)
    //    println(flatten(testNest))
    println(flatten_v2(testNest))
    //    println(otherFlatten(testOther))
    //
    //    println(otherFlatten_v2(testOther))

  }
}

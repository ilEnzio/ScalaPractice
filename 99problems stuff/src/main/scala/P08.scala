import sun.invoke.empty.Empty

object P08 {
  //  P08 (**) Eliminate consecutive duplicates of list elements.
  //    If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
  //
  //    Example:
  //
  //    scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //
  //  res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

  // this is similar to fold; under normal circumstance I should use fold
  def compress[A](l: List[A]): List[A] = {
    def doCompress(list: List[A], acc: List[A] = Nil): List [A] = list match {
      case Nil => acc
      case h :: s :: t  if (h == s) => doCompress(h :: t, acc)
      case h :: t  => doCompress(t, h :: acc)
    }
    doCompress(l).reverse
  }

  // TODO: use fold
  def compress_v2 [A](l: List[A]): List[A] = {
    def folder(state: List[A], value: A) : List[A] = {
      println(s"State: $state, Value: $value")
      state match {
      case Nil => value :: state
      case h:: _ => if (value == h) state else value :: state
      }
    }
    l.foldLeft(List[A]())(folder).reverse
  }


  def main(args: Array[String]): Unit = {


    val test01 = List(Symbol("a"), 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    println(compress(test01))

    println(compress_v2(test01))
  }
}
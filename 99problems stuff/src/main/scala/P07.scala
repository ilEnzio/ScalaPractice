object P07 {
  //
  //  P07 (**) Flatten a nested list structure.
  //  Example:
  //
  //    scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  //
  //  res0: List[Any] = List(1, 1, 2, 3, 5, 8)

  def flatten[Any](nl: List[Any]): Unit = nl match {
    case h :: _ if (h.isInstanceOf[List[Any]]) => println(s"${h.getClass}, got here")
    case Nil => println("end")
    case h :: t => println(h)
      flatten(t)
  }


  def main(args: Array[String]): Unit = {

    val testNest = List(List(1, 1), 2, List(3, List(5, 8)))
    val test1Nest = List(List(1, 1))

    flatten(testNest)
    flatten(test1Nest)


  }
}
object P07 {
  //
  //  P07 (**) Flatten a nested list structure.
  //  Example:
  //
  //    scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  //
  //  res0: List[Any] = List(1, 1, 2, 3, 5, 8)


//  def flatten[Any](nl: List[Any]): Unit = nl match {
//    case h :: _ if (h.isInstanceOf[List[Any]]) => println(s"${h.getClass}, got here")
//    case Nil => println("end")
//    case h :: t => println(h)
//      flatten(t)
//  }

  def flattenv2[Any](l: List[List[Any]]): List[Any] =  {

    def doFlat(list: List[List[Any]], acc: List[Any]): List[Any] = list match {
      case Nil => acc
//      case h :: Nil => h ++ R
      case h :: t if (h.isInstanceOf[List[Any]]) => h ++ doFlat(t, acc)
      case h :: t if (!h.isInstanceOf[List[Any]])=> doFlat(t, h ++ acc)
    }

    doFlat(l, Nil)
  }


  def main(args: Array[String]): Unit = {

    val testNest = List(List(1, 1), 2, List(3, List(5, 8)))
    val test1Nest = List(List(1, 1))


    val test01 = List( 1, 3)
    val test02 = List(4, 6)

    println(test01 ++ test02)

//    println(flattenv2(testNest))



  }
}
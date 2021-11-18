object P02 {


//  P02 (*) Find the last but one element of a list.
//  Example:
//
//    scala> penultimate(List(1, 1, 2, 3, 5, 8))
//
//  res0: Option[Int] = Some(5)

  def findPenultimate[A](l: List[A]): Option[A] = l match {
  case x if (x == List.empty) => None
  case List(_) => None
  case h :: _ :: t if (t == Nil) => Some(h)
  case _ :: t => findPenultimate(t)
}

  def findPenultimate_v2[A](l: List[A]): Option[A] = l match {
    case x if (x == List()) => None
    case List(_) => None
    case _ => Some(l(l.length -2))
  }



  def main(args: Array[String]): Unit = {

    val testList = List(1, 1, 2, 3, 5, 8)
    val test1ele = List(1)
    val testEmpty = List()
    val test2elem = List(2,3)

    println(findPenultimate(testList))
    println(findPenultimate(test1ele))
    println(findPenultimate(testEmpty))
    println((findPenultimate(test2elem)))

    println(findPenultimate_v2(testList))
    println(findPenultimate_v2(test1ele))
    println(findPenultimate_v2(testEmpty))
    println((findPenultimate_v2(test2elem)))


  }


}

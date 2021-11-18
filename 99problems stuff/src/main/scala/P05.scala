

object P05 {

//  P05 (*) Reverse a list.
//  Example:
//
//    scala> reverse(List(1, 1, 2, 3, 5, 8))
//
//  res0: List[Int] = List(8, 5, 3, 2, 1, 1)
def revList[A](l: List[A], acc: List[A] = List() ): List[A] = l match {
  case List() => acc
  case h :: t => revList(t, h :: acc )
}

  def main(args: Array[String]): Unit = {

    val testList = List(1, 1, 2, 3, 5, 8)
    val testEmpty = List()
    val test2ele = List(3,4)
    val test1ele = List(5)

    println(revList(testList))
    println((revList(testEmpty)))
    println(revList(test2ele))
    println(revList(test1ele))
  }
}

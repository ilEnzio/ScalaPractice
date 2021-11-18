object P04 {


//  P04 (*) Find the number of elements of a list.
//    Example:
//
//    scala> length(List(1, 1, 2, 3, 5, 8))
//
//  res0: Int = 6
  def findLen[A](l: List[A], acc: Int = 0 ): Int = l match {
  case Nil => acc
  case _ :: t => findLen(t, acc + 1)
}


  def main(args: Array[String]): Unit = {

    val testList = List(1, 1, 2, 3, 5, 8)
    val testEmpty = List()
    val test1ele = List(3)

    println(findLen(testList))
    println((findLen(testEmpty)))
    println(findLen(test1ele))
  }

}


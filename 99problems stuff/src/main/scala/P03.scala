object P03 {

//  P03 (*) Find the Kth element of a list.
//  By convention, the first element in the list is element 0.
//
//  Example:
//
//    scala> nth(2, List(1, 1, 2, 3, 5, 8))
//
//  res0: Option[Int] = Some(2)

  def getKth[A](k: Int ,l: List[A]): Option[A] =  {
    val validInput = k >= 0 && k < l.length - 1
    if (validInput) Some(l(k)) else None

  }

  def getKth_v2[A](k: Int, l: List[A]): Option[A] = ???
    // make a helper function that has acc that keeps track
    // of the element counter
    // if I rewrite this then I can change Penultimate

  def main(args: Array[String]): Unit = {

    val testList = List(1, 1, 2, 3, 5, 8)
    val testEmpty = List()
    val test1ele = List(1)
    println(getKth(2, testList))
    println(getKth(2, testEmpty))
    println(getKth(2, test1ele))
    println(getKth(-1, testList))
  }

}

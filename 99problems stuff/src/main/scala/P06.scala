object P06 {
  //
  //  P06 (*) Find out whether a list is a palindrome.
  //    Example:
  //
  //    scala> isPalindrome(List(1, 2, 3, 2, 1))
  //
  //  res0: Boolean = true

  // I guess I could use a point for the beg and end.
  // That seems weird though because I have to traverse the list to get
  // to the end I think...

  def isPalindrome(l: List[Int]): Boolean = {
    def validIdxPos(a: Int, b: Int): Boolean = a <= b
    def doIsPal(beg: Int = 0, end: Int = l.length - 1 ): Boolean =
      l match {
        case _ if (l.length <= 1)  => true
        case _ if (!validIdxPos(beg, end)) => true
        case _ if (l (beg) != l(end)) => false
        case _ => doIsPal( beg + 1, end -1)
      }
    doIsPal()
  }

  def main(args: Array[String]): Unit = {

    val testList = List(1, 2, 3, 2, 1)
    val testEmpty = List()
    val test1Ele = List(33)
    val test2EleNotPal = List(2, 5)
    val test2EleIsPal = List(2,2)

    println(isPalindrome(testList))
    println(isPalindrome(testEmpty))
    println(isPalindrome(test1Ele))
    println(isPalindrome(test2EleNotPal))
    println(isPalindrome(test2EleIsPal))
  }
}

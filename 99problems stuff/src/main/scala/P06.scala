import P05.revList

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

  def isPalindrome[A](l: List[A]): Boolean = {
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

  def isPalindrome_v2[A](l: List[A]): Boolean = {
    revList(l) == l
  }

  // I think I could do a v3 using a vector
  def isPalindrome_v3[A](l: List[A]): Boolean = {
    val vl = l.toVector
    def doIsPal[A](v: Vector[A]): Boolean =
      if (v.length <= 1) true
      else if (v.head != v.last) false
      else doIsPal(v.drop(1).dropRight(1))
    doIsPal(vl)
  }

  def main(args: Array[String]): Unit = {

    val testList = List(1, 2, 3, 2, 1)
    val testEmpty = List()
    val test1Ele = List(33)
    val test2EleNotPal = List(2, 5)
    val test2EleIsPal = List(2,2)

    // v1 tests
    println(isPalindrome(testList))
    println(isPalindrome(testEmpty))
    println(isPalindrome(test1Ele))
    println(isPalindrome(test2EleNotPal))
    println(isPalindrome(test2EleIsPal))

    println()

    //v2 tests
    println(isPalindrome_v2(testList))
    println(isPalindrome_v2(testEmpty))
    println(isPalindrome_v2(test1Ele))
    println(isPalindrome_v2(test2EleNotPal))
    println(isPalindrome_v2(test2EleIsPal))

    assert(isPalindrome_v2(testList) == true)
    assert(isPalindrome_v2(testEmpty) == true)
    assert((isPalindrome_v2(test1Ele) == true))
    assert(isPalindrome_v2(test2EleNotPal) == false)
    assert(isPalindrome_v2(test2EleIsPal) == true)

    //v3 test
    assert(isPalindrome_v3(testList) == true)
    assert(isPalindrome_v3(testEmpty) == true)
    assert((isPalindrome_v3(test1Ele) == true))
    assert(isPalindrome_v3(test2EleNotPal) == false)
    assert(isPalindrome_v3(test2EleIsPal) == true)

  }

}
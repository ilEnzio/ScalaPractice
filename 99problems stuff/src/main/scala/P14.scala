object P14 {

  //
  //  P14 (*) Duplicate the elements of a list.
  //    Example:
  //
  //    scala> duplicate(List('a, 'b, 'c, 'c, 'd))
  //
  //  res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

  val doubleIt: Symbol => List[Symbol] = x => List(x, x)

  def duplicate[A](l: List[A]): List[A] = {
    l.map(x => List(x, x)).flatten
  }

  // use foldLeft
  def duplicate_v2[A](l: List[A]): List[A] = {
    l.foldRight(List[A]())( (v, s) => v :: v :: s)
  }

  def duplicate_v3[A](l: List[A]): List[A] = {
    //    l.flatMap( x => List(x, x))
    l.map(x => List(x, x)).flatten
  }

  // use for comprehension
  // with Sally :)
  def duplicate_v4[A](l: List[A]): List[A] = {
    for {
      x <- l
      out <- List(x, x)
    } yield {out }

  }

  def main(args: Array[String]): Unit = {
    val testDupe = List('a, 'b, 'c, 'c, 'd)

    println(duplicate(testDupe))
    println((duplicate_v2(testDupe)))
    println((duplicate_v4(testDupe)))


  }
}
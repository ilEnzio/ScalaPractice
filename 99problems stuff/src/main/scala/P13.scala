object P13 {

  //  P13 (**) Run-length encoding of a list (direct solution).
  //    Implement the so-called run-length encoding data compression method directly. I.e. don’t use other methods you’ve written (like P09’s pack); do all the work directly.
  //
  //  Example:
  //
  //    scala> encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //
  //  res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  def encodeDirect[A](l: List[A]): List[(Int, A)] = {
    def doEncDir(list: List[A], subAcc: (Int, Option[A]), acc: List[(Int, Option[A])]): List[(Int, Option[A])] = {
      list match {
        case Nil => acc
        case h :: s :: t if (h == s) => doEncDir(h :: t, (subAcc._1 + 1, Some(h)), acc)
        case _ :: t  => doEncDir(t, (0, None), (subAcc._1 + 1, subAcc._2) :: acc)
      }
    }
    // used None because I couldn't figure out how to kick off the recursion generically
    val temp = doEncDir(l, (0, None), List.empty[(Int, Option[A])]).reverse
    println(temp)
    temp.map(x => (x._1, x._2.get ))
  }

  def encodeDirect_v02[A](l: List[A]): List[(Int,A)] = {
    def doEncDir(list: List[A], tupAcc: (Int, Option[A]), acc: List[(Int, Option[A])]): List[(Int, Option[A])] = {
      //      println(s"$list ------ $acc")
      list match {
        case Nil => acc
        case h :: s :: t if (h == s) => doEncDir(h::t, (tupAcc._1 + 1, Some(h)), acc)
        case h :: t => doEncDir(t, (0, None), (tupAcc._1 + 1, Some(h)) :: acc )
      }
    }
    doEncDir(l, (0, None), Nil).reverse.map(x => (x._1, x._2.get))
  }

  def main(args: Array[String]): Unit = {

    val test01 = encodeDirect_v02(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    println(test01)
  }
}
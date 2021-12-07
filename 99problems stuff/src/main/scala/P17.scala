object P17 {
  //  P17 (*) Split a list into two parts.
  //    The length of the first part is given. Use a Tuple for your result.
  //
  //  Example:
  //
  //    scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //
  //  res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

  //  def split[A](n: Int, l: List[A]): (List[A], List[A]) = {
  //
  //    def doSplit(list: List[(A, Int)], acc: (List[A], List[A])): (List[A], List[A]) = list match {
  //      case Nil => acc //
  //      case (h: (A, Int)) :: t if (h._2 < n) => doSplit(t, (List(h._1) ++ acc._1, acc._2))
  //      case x =>  (acc._1, x.unzip._1)
  //    }
  //    doSplit(l.zipWithIndex, (List[A](), List[A]()))
  //  }

  // with unzip
  def split_v2[A](n: Int, l: List[A]): (List[A], List[A]) = {

    def doSplit(list: List[(A, Int)], acc: (List[(A, Int)], List[(A, Int)])): (List[A], List[A]) = list match {
      case Nil => (acc._1.unzip._1, acc._2.unzip._1)
      case h :: t if (h._2 < n) => doSplit(t, (h :: acc._1, acc._2))
      case x => (acc._1.unzip._1.reverse, x.unzip._1)
    }
    doSplit(l.zipWithIndex, (List[(A, Int)](), List[(A, Int)]()))
  }

  // try with foldLeft. YUCK!!!! Super gross...
  def split_v3[A](n: Int, l: List[A]): (List[A], List[A]) = {
    // List((a, 0), (b,1), (c, 2)....))
    val t = l.zipWithIndex.foldLeft((List[A](), List[A]())) { case (state, value) =>
      println(s"$value ------ $state")
      value._2 match {
        case x if ((x: Int) < n) => (value._1 :: state._1, state._2)
        case _ => (state._1, value._1 :: state._2)
      }
      // (List[A], x: Int) (List [A]))
    }
    val correct = (t._1.reverse, t._2.reverse)
    correct
  }

  // (List[A], x), List[A])
  //
  //  def split_v4[A](n: Int, l: List[A]): (List[A], List[A]) = {
  //
  //    val t = l.zipWithIndex.foldLeft((List[A](), List[A]())) { case (state, value) =>
  //      println(s"$value ------ $state")
  //      value._2 if ( value._2 < n) value._2 match {
  //        case x if ((x: Int) < n) => (value._1 :: state._1, state._2)
  ////        case _ => (state._1, value._1 :: state._2)
  //      } else value._2 match {
  //          case _ => (state._1, value._1 :: state._2)
  //    }
  //
  //    }
  //    val correct = (t._1.reverse, t._2.reverse)
  //    correct
  //  }



  def main(args: Array[String]): Unit = {

    val testList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    //    println(split(3, testList))

    println(split_v2(3, testList))
    println(split_v2(5, testList))
    println(split_v3(3, testList))

    println()
    println(split_v3(3, testList))

  }
}
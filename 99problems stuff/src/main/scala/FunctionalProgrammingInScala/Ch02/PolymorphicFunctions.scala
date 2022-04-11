package FunctionalProgrammingInScala.Ch02

object PolymorphicFunctions {

  def findFirst[A] (as: Array[A], f: A => Boolean): Int = {
    def loop(n: Int): Int = {
      if (n >= as.length) -1
      else if (f(as(n))) n
      else loop(n + 1)
    }
    loop(0)
  }

def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {

  val (result, _) = as.foldLeft((true, Option.empty[A])){ case ((bs, ele), value) => bs match {
    case false => (false, Some(value))
    case _ => {
      if (ordered(ele.get, value)) (true, Some(value))
      else (false, Some(value))
    }
  }
  }
  result

}


  // book answer
  def isSorted2[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean = {
      if (n >= as.length - 1) true
      else if (gt(as(n), as(n + 1))) false
      else go(n + 1)
    }

    go(0)
  }
  def main(args: Array[String]): Unit = {

  }

}

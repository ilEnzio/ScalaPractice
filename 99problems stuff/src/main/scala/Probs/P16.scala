package Probs

object P16 {
  // https://docs.banno.com/learning/scala/module/99problems/#p16--drop-every-nth-element-from-a-list
  //  P16 (**) Drop every Nth element from a list.
  //  Example:
  //
  //    scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  //
  //  res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  def drop[A](n: Int, l: List[A]): List[A] = {
    def doDrop[A, B](list: List[A], idx: Int = 0, acc: List[(A)] = Nil): List[A] = {
      //      println(s"$list ---- $acc")
      list match {
        case Nil => acc.reverse
        case _ :: t if (idx + 1) % n == 0 => doDrop(t, idx + 1, acc)
        case h :: t => doDrop(t, idx + 1, h :: acc)
      }
    }

    doDrop(l)

  }

  def main(args: Array[String]): Unit = {

    val testList = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

    println(drop(3, testList))
  }


}

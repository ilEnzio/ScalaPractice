package EssentialScalaBookEx.Ch05

object ABetterAbstraction {
  //  def abstraction(end: Int, f: ???): Int =
  //    this match {
  //      case End => end
  //      case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
  //    }

  sealed trait IntList {
    //      def length: Int = {
    //        def doLen(list: IntList, acc: Int = 0): Int = list match {
    //          case End => acc
    //          case Pair(_, t) => doLen(t, acc + 1)
    //        }
    //        doLen(this)
    //      }

    def length: Int = {
      this.fold_v2(0, (_, y) => y + 1)
    }

    def prod: Int = {
      this.fold_v2(1, (x, y) => x * y)
    }

    def sum: Int = {
      this.fold_v2(0, (x, y) => x + y)
    }

//    def double: IntList = {
//      def doDouble(l: IntList, multiplier: Int, acc: IntList = End ): IntList = l match {
//        case End => acc
//        case Pair(h, t) => doDouble(t, multiplier, Pair (multiplier * h, acc))
//      }
//      val firstPass = doDouble(this, 2)
//      doDouble(firstPass, 1)
//    }

    def double: IntList = {
      this.fold_v3[IntList](End, (h, t) => Pair(h * 2, t.double) )
    }

    def fold(end: Int, f: (Int, Int) => Int): Int = this match {
      case End => end
      case Pair(h, _) => f(h, fold(end, f))
    }
    // make tail rec
    def fold_v2(end: Int, f: (Int, Int) => Int): Int = {
      def dofold(p: IntList = this, acc: Int): Int =
        p match {
          case End => acc
          case Pair(h, t) => f(h, dofold(t, acc))
        }
      dofold(this, end)
    }

    def fold_v3[A](end: A, f: (Int, A) => A): A = {
      def dofold( acc: A, p: IntList): A =
        p match {
          case End => acc
          case Pair(h, t) => f(h, dofold( acc, t))
        }
      dofold(end, this)
    }

    def fold_vBook[A](end: A, f: (Int, A) => A): A =
      this match {
        case End => end
        case Pair(hd, tl) => f(hd, tl.fold_vBook(end, f))
      }

  }

  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList


  def main(args: Array[String]): Unit = {


    val example = Pair(1, Pair(2, Pair(3, End)))
    val example02 = Pair(1, Pair(2, Pair(3, Pair(4, End))))

    // test length
    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End.length == 0)

    // test product
    assert(example.prod == 6)
    assert(example.tail.prod == 6)
    assert(End.prod == 1)

    println(example02.fold_v3[Int](1, (a, b)=> a * b))
    println(example02.fold_v3[Int](0, (a, b)=> a + b ))

    println()

    println(example02.sum)
    println(example02.prod)
    println(example02.length)
    println(example02.double)

  }


}
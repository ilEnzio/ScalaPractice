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
        this.fold_v2((_, y) => y + 1, 0)
      }

      def prod: Int = {
        this.fold_v2((x, y) => x * y, 1)
      }

      def sum: Int = {
        this.fold_v2((x, y) => x + y, 0)
      }

      def double: IntList = {
        def doDouble(l: IntList, multiplier: Int, acc: IntList = End ): IntList = l match {
          case End => acc
          case Pair(h, t) => doDouble(t, multiplier, Pair (multiplier * h, acc))
        }
        val firstPass = doDouble(this, 2)
        doDouble(firstPass, 1)
      }

      def fold(end: Int, f: (Int, Int) => Int): Int = this match {
        case End => end
        case Pair(h, t) => f(h, fold(end, f))
      }

      def fold_v2(f: (Int, Int) => Int, end: Int): Int = {
        def dofold(p: IntList = this, acc: Int): Int =
          p match {
          case End => acc
          case Pair(h, t) => f(h, dofold(t, acc))
        }
        dofold(this, end)
      }

      def fold_v3[A](f: (Int, A) => A, end: A): A = {
        def dofold(p: A, acc: A): A =
          p match {
            case End => acc
            case Pair(h, t) => f(h, dofold(t, acc))
          }
        dofold(this, end)
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

      println(example02.fold_v2((a, b)=> a * b, 1 ))
      println(example02.fold_v2((a, b)=> a + b, 0 ))

      println(example02.sum)
      println(example02.prod)
      println(example02.length)

    }


}

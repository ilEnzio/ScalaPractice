package EssentialScalaBookEx.Ch04

object AListOfMethods {

  sealed trait IntList {
    def length: Int = {
      def doLen(list: IntList, acc: Int = 0): Int = list match {
        case End => acc
        case Pair(_, t) => doLen(t, acc + 1)
      }
      doLen(this)
    }

    def prod: Int = {
      def doProd(l: IntList, acc: Int = 1): Int = l match {
        case End => acc
        case Pair(h, t) => doProd(t, acc * h)
      }
      doProd(this)
    }

    def double: IntList = {
      def doDouble(l: IntList, multiplier: Int, acc: IntList = End ): IntList = l match {
        case End => acc
        case Pair(h, t) => doDouble(t, multiplier, Pair (multiplier * h, acc))
      }
      val firstPass = doDouble(this, 2)
      doDouble(firstPass, 1)
    }
  }


  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  def main(args: Array[String]): Unit = {
    val example = Pair(1, Pair(2, Pair(3, End)))

    // test length
    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End.length == 0)

    // test product
    assert(example.prod == 6)
    assert(example.tail.prod == 6)
    assert(End.prod == 1)

    // test double
//    assert(example.double == Pair(2, Pair(4, Pair(6, End))))
    assert(example.tail.double == Pair(4, Pair(6, End)))
    assert(End.double == End)
  }
}

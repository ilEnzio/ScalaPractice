package EssentialScalaBookEx.Ch05

object GenericList {

  sealed trait Result[A]
  case class Success[A](result: A) extends Result[A]
  case class Failure[A](reason: String) extends Result[A]

  sealed trait LList[A] {
    def length: Int = {
      def doLen(l: LList[A], acc: Int): Int = l match {
        case End() => acc
        case Pair(_, t) => doLen(t, acc + 1)
      }
      doLen(this, 0)
    }

    def contains(target: A): Boolean = this match {
      case End() => false
      case Pair(h, _ ) if(h == target) => true
      case Pair(_, t)  => t.contains(target)
    }

    def apply(k: Int): Result[A] = {
      def doAtIdx(l: LList[A], idx: Int): Result[A] = l match {
        case End() => Failure("Index out of bounds")
        case Pair(h, _) if (idx == k) => Success(h)
        case Pair(_, t) => doAtIdx(t, (idx + 1))
      }
      doAtIdx(this, 0)
    }


  }
  case class End[A]() extends LList[A] // how come this can no longer be and object?
  final case class Pair[A](head: A, tail: LList[A]) extends LList[A]

  def main(args: Array[String]): Unit = {

    val example = Pair(1, Pair(2, Pair(3, End())))
    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End().length == 0)


    // test contains
    //    val example = Pair(1, Pair(2, Pair(3, End())))
    assert(example.contains(3) == true)
    assert(example.contains(4) == false)
    assert(End().contains(0) == false)

    // test apply/atIndex
    assert(example(0) == Success(1))
    assert(example(1) == Success(2))
    assert(example(2) == Success(3))
    assert(example(3) == Failure("Index out of bounds"))

  }
}
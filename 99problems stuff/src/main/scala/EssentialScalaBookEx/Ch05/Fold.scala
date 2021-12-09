package EssentialScalaBookEx.Ch05

object Fold {

  sealed trait LinkedList[A] {
    def fold[B](end: B, f: (A, B) => B): B =  this match {
      case End() => end
      case Pair(h, t) => f(h, t.fold(end, f))
    }

    def fold_v3[B](end: B, f: (A, B) => B): B = {
      def dofold( acc: B, p: LinkedList[A]): B =
        p match {
          case End() => acc
          case Pair(h, t) => f(h, dofold( acc, t))
        }
      dofold(end, this)
    }

  }





  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]

}

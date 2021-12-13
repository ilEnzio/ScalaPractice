package EssentialScalaBookEx.Ch05

object MappingLists {


  sealed trait LinkedList[A] {
    def fold[B](end: B, f: (A, B) => B): B = this match {
      case End() => end
      case Pair(h, t) => f(h, t.fold(end, f))
    }

    def map[B](f: A => B): LinkedList[B] = this match {
      case End() => End[B]()
      case Pair(h, t) => Pair(f(h), t.map(f))
    }
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]

  def main(args: Array[String]): Unit = {


    val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))

    println(list.map { x => x * 2 }) // double
    println(list.map {x => x +1})
    println(list.map({_/3}))

  }


}
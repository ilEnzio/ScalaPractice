package EssentialScalaBookEx.Ch05

object Tree {

  sealed trait Tree[A]{
    def fold[B](end: A => B)(f: (B, B) => B): B = this match {
      case Leaf(x: A) => end(x)
      case Node(l:B , r: B) => f(l.fold(end)(f), r.fold(end)(f))
    }
  }
  case class Leaf[A](value: A) extends Tree[A]
  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]


  def main(args: Array[String]): Unit = {

    val tree: Tree[String] =
      Node(Node(Leaf("To"), Leaf("iterate")),
        Node(Node(Leaf("is"), Leaf("human,")),
          Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))


    println(tree.fold[String]({case x => x}){case (x,y) => s"$x $y"}) // still a little confused about x => x
  }
}

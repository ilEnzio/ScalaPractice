package ScalaWithCats.Ch03

object BranchingOutWFunctors {


//  Write a Functor for the following binary tree data type.
//  Verify that the code works as expected on instances of Branch and Leaf:
  import cats.Functor


  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A])
    extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]


  // I need to map over the Tree
  // rule for Branch -> recur over left and right
  // Rule for leaf -> change value

  implicit val treeFunctor: Functor[Tree] = {
    new Functor[Tree] {
      override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
        case x: Branch[A] => Branch(map(x.left)(f), map(x.left)(f))
        case x: Leaf[A] => f.andThen(Leaf[B])(x.value)// Leaf(f(x.value))
      }
    }

  }

  // book solution
  import cats.Functor

//  implicit val treeFunctor: Functor[Tree] =
//    new Functor[Tree] {
//      def map[A, B](tree: Tree[A])(func: A => B): Tree[B] =
//        tree match {
//          case Branch(left, right) =>
//            Branch(map(left)(func), map(right)(func))
//          case Leaf(value) =>
//            Leaf(func(value))
//        }
//    }

  object Tree {
    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
      Branch(left, right)

    def leaf[A](value: A): Tree[A] =
      Leaf(value)
  }

  Tree.leaf(100).map(_ * 2)
  // res9: Tree[Int] = Leaf(200)

  Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2)
  // res10: Tree[Int] = Branch(Leaf(20), Leaf(40))



def main(args: Array[String]): Unit = {
//  Branch(Leaf(10), Leaf(20)).map(_ * 2)

  // error: value map is not a member of repl.Session.App0.Branch[Int]
  // Branch(Leaf(10), Leaf(20)).map(_ * 2)
  // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^






}

}

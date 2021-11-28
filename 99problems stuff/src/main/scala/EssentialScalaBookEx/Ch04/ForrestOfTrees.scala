package EssentialScalaBookEx.Ch04

object ForrestOfTrees {

  sealed trait Tree{
    def sum: Int
  }
  final case class Node(left: Tree, right: Tree) extends Tree{
    override def sum: Int = {
      def doSum(t: Tree, acc: Int): Int = t match {
        case t: Leaf => t.value + acc
        case t: Node => doSum(t.left, acc) + doSum(t.right, acc)
      }
      doSum(this, 0)
    }
  }

  final case class Leaf(value: Int) extends Tree {
    override def sum: Int = value
  }

  def main(args: Array[String]): Unit = {

  }

}

package ScalaWithCats.Ch02

object AddingAllTheThings {


  import cats._
  import cats.implicits._

  case class Order(totalCost: Double, quantity: Double)

  object Order extends {
    implicit val orderMonoid = new Monoid[Order] {
      def combine(x: Order, y: Order): Order = {
        val newTotal = x.totalCost + y.totalCost
        val newQuant = x.quantity + y.quantity
        Order(newTotal, newQuant)
      }
      def empty = Order(0, 0)
    }
  }



  def add(items: List[Int]): Int = {
    items.foldLeft(Monoid[Int].empty){(s, v) =>
      s |+| v
    }
  }


  def add2[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items.foldLeft(monoid.empty){ _ |+| _}
  }

  // context bound syntax
  def add3[A: Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty){ _ |+| _ }
  }

  def main(args: Array[String]): Unit = {
    val test1 = List(1, 2, 3)

    println(add2(test1))

    val test2 = add2(List(Some(1),None, Some(2), Some(3)))

    val testOrd1 = Order(3, 3)
    val testOrd2 = Order(4, 4)

    val ordList = List(testOrd1, testOrd2)

    println(add2(ordList))


  }
}

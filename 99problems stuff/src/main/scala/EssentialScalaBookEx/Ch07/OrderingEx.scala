package EssentialScalaBookEx.Ch07

object OrderingEx {

  import scala.math.Ordering
  val minOrdering = Ordering.fromLessThan[Int](_ < _)
  // minOrdering: scala.math.Ordering[Int] = scala.math.Ordering$$anon$9@6b14fb5c

  val maxOrdering = Ordering.fromLessThan[Int](_ > _)
  // maxOrdering: scala.math.Ordering[Int] = scala.math.Ordering$$anon$9@3924e0f4

  val test1 = List(3, 4, 2).sorted(minOrdering)
  // res0: List[Int] = List(2, 3, 4)

  val test2 = List(3, 4, 2).sorted(maxOrdering)
  // res1: List[Int] = List(4, 3, 2)


  implicit val ordering = Ordering.fromLessThan[Int](_ < _)

  val test3 = List(2, 4, 3).sorted

  val test4 = List(1, 7, 5).sorted

  def main(args: Array[String]): Unit = {
    println(test1)
    println(test2)

    println(test3)
    println(test4)
  }

}

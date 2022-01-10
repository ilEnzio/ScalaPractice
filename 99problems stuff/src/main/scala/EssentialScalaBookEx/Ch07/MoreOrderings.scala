package EssentialScalaBookEx.Ch07

object MoreOrderings {


  val absOrdering = Ordering.fromLessThan[Int]({ (x, y) => Math.abs(x) < Math.abs(y) })


  implicit val absOrdering1 = Ordering.fromLessThan[Int]{ (x, y) => Math.abs(x) < Math.abs(y)}

  def main(args: Array[String]): Unit = {
    assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))

    assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))
  }
}

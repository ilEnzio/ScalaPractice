package EssentialScalaBookEx.Ch05

object GenericSumType {
// 5.4.3.1

  sealed trait Sum[+A, +B]{
//    def fold
    def fold[C](fail: A => C, success: B => C): C =
      this match {
        case Fail(a) => fail(a)
        case Success(b) => success(b)
      }

    def map[C](f: B => C): Sum[A, C] = this match {
      case Fail(a) => Fail(a)
      case Success(b) => Success(f(b))
    }

    def flatMap[AA >: A, C](f: B => Sum[AA, C] ): Sum[AA, C] = this match {
      case Fail(a) => Fail(a)
      case Success(b) => f(b) // i"m confused by this review if you can
    }
  }

  case class Fail[A,B](value: A) extends Sum[A, B]
  case class Success[A,B](value: B) extends Sum[A, B]




  def main(args: Array[String]): Unit = {


    Left[Int, String](1).value
    // res9: Int = 1

    Right[Int, String]("foo").value
    // res10: String = foo

    val sum: Sum[Int, String] = Right("foo")
    // sum: sum.Sum[Int,String] = Right(foo)

    sum match {
      case Left(x) => x.toString
      case Right(x) => x
    }
    // res11: String = foo

  }

}

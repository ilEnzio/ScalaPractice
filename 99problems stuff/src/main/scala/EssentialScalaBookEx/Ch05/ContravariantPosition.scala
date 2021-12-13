package EssentialScalaBookEx.Ch05

object ContravariantPosition {

  sealed trait Sum[+A, +B] {
    def flat[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] = this match {
      case Fail(a) => Fail(a)
      case Success(b) => f(b)
    }
  }



  case class Fail[A,B](value: A) extends Sum[A, B]
  case class Success[A,B](value: B) extends Sum[A, B]


  def main(args: Array[String]): Unit = {

  }

}

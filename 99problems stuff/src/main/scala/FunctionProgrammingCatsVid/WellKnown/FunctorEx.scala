package FunctionProgrammingCatsVid.WellKnown

object FunctorEx {

  import cats._
  import cats.implicits._
  import java.nio.charset.StandardCharsets
  import java.security.MessageDigest

  class Secret[A](val value: A) {
    private def hashed: String = {
      val s = value.toString
      val bytes = s.getBytes(StandardCharsets.UTF_8)
      val d = MessageDigest.getInstance("SHA-1")
      val hashBytes = d.digest(bytes)
      new String(hashBytes, StandardCharsets.UTF_8)

    }


    override def toString: String = hashed
  }


  object Secret {
    implicit val secretFunctor = new Functor[Secret] {
      override def map[A, B](fa: Secret[A])(f: A => B): Secret[B] =
        new Secret(f(fa.value))
    }
  }


  //
  val optionFunctor: Functor[Option] = new Functor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
      case None => None
      case Some(x) => Some(f(x))
    }
  }


  val listFunctor: Functor[List] = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa match {
      case Nil => Nil
      case h :: t => f(h) :: map(t)(f)
    }
  }

  def main(args: Array[String]): Unit = {

    val ergSecret: Secret[String] = new Secret("erlr's Secret")

    println(ergSecret.value)
    println(ergSecret)

    println(Functor[Secret].map(ergSecret)(_.toUpperCase).value)
    println(Functor[Secret].map(ergSecret)(_.toUpperCase))


    println(optionFunctor.map(Some(3))(_ + 1))
    println(listFunctor.map(List(1,2,3))(_ * 2))
  }

}

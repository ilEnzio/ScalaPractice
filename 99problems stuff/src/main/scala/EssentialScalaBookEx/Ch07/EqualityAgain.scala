package EssentialScalaBookEx.Ch07

object EqualityAgain {

  case class Person(name: String, email: String)

  trait Equal[A] {
    def equal(v1: A, v2: A): Boolean
  }

  object EmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email
  }

  implicit object NameEmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email && v1.name == v2.name
  }


  object Eq {
    def apply[A](v1: A, v2: A)(implicit in: Equal[A]): Boolean =
      in.equal(v1, v2)
  }

  Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))

  def main(args: Array[String]): Unit = {



  }
}

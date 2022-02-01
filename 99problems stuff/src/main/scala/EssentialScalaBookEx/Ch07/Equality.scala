package EssentialScalaBookEx.Ch07

object Equality {

  sealed trait Equal[A] {
    def equal(v1: A, v2: A): Boolean
    def equal2: A => A=> Boolean

  }

  case class Person(name: String, email: String)

  object Person {
    def equal(p1: Person, p2: Person): Boolean =
      p1.email == p2.email
  }

  object PersonEqualByNameAndEmail extends Equal[Person] {
    def equal(p1: Person, p2: Person): Boolean =
      p1.name == p2.name && p1.email == p2.email

    def equal2: Person => Person => Boolean = {
      p1: Person => p2: Person => p1.email == p2.email
    }

  }

  def main(args: Array[String]): Unit = {
  }

}




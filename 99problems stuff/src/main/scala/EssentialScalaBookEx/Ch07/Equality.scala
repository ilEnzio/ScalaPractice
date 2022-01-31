package EssentialScalaBookEx.Ch07

object Equality {

  sealed trait Equal[A] {
    def equal(v1: A, v2: A): Boolean
  }

  case class Person(name: String, email: String)

  object Person {
    def equal(p1: Person, p2: Person): Boolean =
      p1.email == p2.email
  }

  object PersonEqualByNameAndEmail extends Equal[Person] {
    def equal(p1: Person, p2: Person): Boolean =
      p1.name == p2.name && p1.email == p2.email
  }

  def main(args: Array[String]): Unit = {
  }

}




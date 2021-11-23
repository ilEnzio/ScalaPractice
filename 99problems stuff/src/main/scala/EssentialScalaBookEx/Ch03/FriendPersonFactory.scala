package EssentialScalaBookEx.Ch03

object FriendPersonFactory {

  case class Person(firstName: String, lastName: String) {
    def name = s"$firstName $lastName"
  }

  object Person {
    def apply(name: String): Person = {
      val wholeName = name.split(" ")
      // what would I used to account for the instance where there
      // are not two parts? Option?
      new Person(wholeName(0), wholeName(1))
    }
  }
  def main(args: Array[String]): Unit = {

    val testPerson = Person("Il Enzio")

    println(testPerson.firstName)
    println(testPerson.lastName)
    assert(testPerson.firstName == "Il")
    assert(testPerson.lastName == "Enzio")

  }

}

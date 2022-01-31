package EssentialScalaBookEx.Ch07

object CreatingTypeClass {

  trait HtmlWriteable {
    def toHtml: String
  }

  object HtmlWriter {
    def write(in: Any): String =
      in match {
        case Person(name, email) => ???
//        case d: Date => ???
        case _ => throw new Exception(s"Can't render ${in} to HTML")
      }
  }

  final case class Person(name: String, email: String) extends HtmlWriteable {
    def toHtml = s"<span>$name &lt;$email&gt;</span>"
  }

  trait HtmlWriter[A] {
    def write(in: A): String
  }

  object PersonWriter extends HtmlWriter[Person] {
    def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
  }

  import java.util.Date

  object DateWriter extends HtmlWriter[Date] {
    def write(in: Date) = s"<span>${in.toString}</span>"
  }

  object ObfuscatedPersonWriter extends HtmlWriter[Person] {
    def write(person: Person) =
      s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
  }

  def main(args: Array[String]): Unit = {
    println(Person("John", "john@example.com").toHtml)

    PersonWriter.write(Person("John", "john@example.com"))

    DateWriter.write(new Date)

    ObfuscatedPersonWriter.write(Person("John", "john@example.com"))

  }
}

package ScalaWithCats.Ch01


object TypeClassExample {

  // Define a very simple JSON AST
  sealed trait Json
  final case class JsObject(get: Map[String, Json]) extends Json
  final case class JsString(get: String) extends Json
  final case class JsNumber(get: Double) extends Json
  final case object JsNull extends Json
  // The "serialize to JSON" behaviour is encoded in this trait
  trait JsonWriter[A] {
    def write(value: A): Json
  }

  object Json {
    def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }

  final case class Person(name: String, email: String)

  object JsonWriterInstances {
    implicit val stringWriter: JsonWriter[String] =
      new JsonWriter[String] {
        def write(value: String): Json =
          JsString(value)
      }
    implicit val personWriter: JsonWriter[Person] =
      new JsonWriter[Person] {
        def write(value: Person): Json =
          JsObject(Map(
            "name" -> JsString(value.name),
            "email" -> JsString(value.email)
          ))
      }
    // etc...
  }

  import JsonWriterInstances._
  Json.toJson(Person("Dave", "dave@example.com"))
  // res1: Json = JsObject(
  // Map("name" -> JsString("Dave"), "email" -> JsString("dave@example
  //.com"))
  // )


  // count, list.head, take, toString, isEmpty


  // Interface Syntax

  object JsonSyntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit w: JsonWriter[A]): Json =
        w.write(value)
    }
  }

//  import JsonWriterInstances._
  import JsonSyntax._

  Person("Dave", "dave@example.com").toJson
  // res3: Json = JsObject(
  //   Map("name" -> JsString("Dave"), "email" -> JsString("dave@example.com"))
  // )

//  Person("Dave", "dave@example.com").toJson(personWriter)


    // implicitly Method
//
  def implicitly[A](implicit value: A): A =
    value


//  import JsonWriterInstances._

//  implicitly[JsonWriter[String]]
  // res5: JsonWriter[String] = repl.Session$App0$JsonWriterInstances$$anon$1@76f60d45

//  val test = implicitly[JsonWriter[Person]]
//  val willItWork = JsonSyntax.toJson(Person("Erlr", "test@whateva"))

  implicit def optionWriter[A]
  (implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {
      def write(option: Option[A]): Json =
        option match {
          case Some(aValue) => writer.write(aValue)
          case None => JsNull
        }
    }

  def main(args: Array[String]): Unit = {

    val test = Json.toJson(Option("A string"))
    println(test)
  }

}
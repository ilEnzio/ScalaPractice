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


}
package EssentialScalaBookEx.Ch04

object Json {
//
//  sealed trait EJson{
//    def isEmpty: Boolean
//    def keyValue: (String, EJson)
//  }
//
//  case class EmptyJ() extends EJson{
//    def isEmpty: Boolean = true
//    override def keyValue = ???
//    override def toString: String = "{}"
//  }
//  case class NonEmptyJ(kv: (String, EJson), next: EJson) extends EJson {
//
//    override def keyValue: (String, EJson) = kv
//
//    override def toString: String = {
//      // TODO: Got wrecked right here.  don't understand recursive data structures :(
//      def doConcat(eJson: EJson, acc: String = s"\"${this.kv._1}\": ${this.kv._2}"): String = {
//        if (next.isEmpty) acc
//        else doConcat(next, s"$acc, \"${eJson.keyValue._1}\": ${eJson.keyValue._2}")
//      }
//      //      s"{\"$key\": $value}"
//      s"{${doConcat(this)}}"
//    }
//    override def isEmpty: Boolean = false
//  }
//  case class Eval(value: Any) extends EJson{
//    override def isEmpty: Boolean = ???
//    override def keyValue = ???
//  }
//
//  sealed trait EList[A] extends EJson {
//    def head: A
//    def tail: EList[A]
//    def isEmpty: Boolean
//    override def toString: String = super.toString
//  }
//
//  case class EmptyL[A]() extends EList[A] {
//    override def head: A = ???
//    override def tail: EList[A] = ???
//    override def toString: String = "[]"
//    override def isEmpty: Boolean = true
//    override def keyValue = ???
//
//  }
//
//  case class ConsE[A](value: A, next: EList[A] ) extends EList[A] {
//    override def head: A = value
//    override def tail: EList[A] = next
//    override def isEmpty: Boolean = false
//    override def keyValue = ???
//
//
//    override def toString: String =  {
//      def doConcat(eList: EList[A], acc: String = this.head.toString): String = {
//        if (eList.isEmpty) acc
//        else doConcat(eList.tail, s"$acc, ${eList.head}")
//      }
//      s"[${doConcat(this.tail)}]"
//    }
//  }

  //JsonObject
  //  String,
  //  Int,Boolean,String,List, JsonObject, null

  sealed trait JsonValue{
    override def toString: String = this match {
      case JsonNul => "{}"
      case JsonDouble(d) => s"$d"
      case JsonBool(b) => s"$b"
      case JsonString(x) => s"\"$x\""
      case JsonList(l) => s"[${l.map(x => x.toString).addString(new StringBuilder(), ", ")}]"
      case JsonObject(json) => s"{${json.map(a => s"${a._1.toString}: ${a._2.toString}").addString(new StringBuilder(), ", ")}}"
    }
  }

  final case object JsonNul extends JsonValue
  final case class JsonDouble(d: Double) extends JsonValue
  final case class JsonBool(b: Boolean) extends JsonValue
  final case class JsonString(s: String) extends JsonValue
  final case class JsonList(l: List[JsonValue])extends JsonValue
  final case class JsonObject(json: List[(JsonString, JsonValue)]) extends JsonValue

  def main(args: Array[String]): Unit = {

    val test2 = JsonObject(List(
      (JsonString("outer"),
        JsonObject(List(
          (JsonString("testString01"), JsonBool(false)),
          (JsonString("ListKey"), JsonList(List(JsonBool(false), JsonDouble(15.4), JsonString("robert"))))
        )))
    ))

    println(test2)

  }

}
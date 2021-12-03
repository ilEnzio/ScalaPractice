package EssentialScalaBookEx.Ch04

object Json {

  sealed trait EJson{
    def isEmpty: Boolean
    def keyValue: (String, EJson)
  }

  case class EmptyJ() extends EJson{
    def isEmpty: Boolean = true
    override def keyValue = ???
    override def toString: String = "{}"
  }
  case class NonEmptyJ(kv: (String, EJson), next: EJson) extends EJson {

    override def keyValue: (String, EJson) = kv

    override def toString: String = {
// TODO: Got wrecked right here.  don't understand recursive data structures :( 
      def doConcat(eJson: EJson, acc: String = s"\"${this.kv._1}\": ${this.kv._2}"): String = {
        if (next.isEmpty) acc
        else doConcat(next, s"$acc, \"${eJson.keyValue._1}\": ${eJson.keyValue._2}")
      }
      //      s"{\"$key\": $value}"
      s"{${doConcat(this)}}"
    }
    override def isEmpty: Boolean = false
  }
  case class Eval(value: Any) extends EJson{
    override def isEmpty: Boolean = ???
    override def keyValue = ???
  }

  sealed trait EList[A] extends EJson {
    def head: A
    def tail: EList[A]
    def isEmpty: Boolean
    override def toString: String = super.toString
  }

  case class EmptyL[A]() extends EList[A] {
    override def head: A = ???
    override def tail: EList[A] = ???
    override def toString: String = "[]"
    override def isEmpty: Boolean = true
    override def keyValue = ???

  }

  case class ConsE[A](value: A, next: EList[A] ) extends EList[A] {
    override def head: A = value
    override def tail: EList[A] = next
    override def isEmpty: Boolean = false
    override def keyValue = ???


    override def toString: String =  {
      def doConcat(eList: EList[A], acc: String = this.head.toString): String = {
        if (eList.isEmpty) acc
        else doConcat(eList.tail, s"$acc, ${eList.head}")
      }
      s"[${doConcat(this.tail)}]"
    }
  }



// a value can be a list or a EJson
// so value would extend both list and EJson

  def main(args: Array[String]): Unit = {

    val aList = ConsE(1, ConsE(2, ConsE(3, EmptyL())))
    val emptyList = EmptyL()
    val emptyJson = EmptyJ()
    val test1  = NonEmptyJ(("erle", aList), EmptyJ())
    val nestTest = NonEmptyJ(("nestedErle", test1), EmptyJ())
    val addToNest = NonEmptyJ(("1",test1), test1)
//    val test2JS = NonEmptyJ(("1", test1), NonEmptyJ(("2", test1), EmptyJ()))
    println(emptyList)
    println(aList)
    println(emptyJson)
    println(test1)

    println(nestTest)
//    println(addToNest)
//    println(test12JS)
  }



}

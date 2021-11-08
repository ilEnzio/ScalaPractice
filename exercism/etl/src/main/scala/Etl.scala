
object Etl {
  //  from Map(
  //  1 -> ("A", "E", "I", "O", "U", "L", "N", "R", "S", "T")
  // )
  // to Map(a -> 1, e -> 1...

//  def transform(oldMap: Map[Int, Seq[String]]): Map[String, Int]= {
//    for {
//      k <- oldMap.keys
//      v <- oldMap.values
//    }
//  }
def main(args: Array[String]): Unit = {
  val test = Map(1 -> Seq("A", "E", "I", "O", "U"))

  val keyTest = test.keys

  println(keyTest)
}
}


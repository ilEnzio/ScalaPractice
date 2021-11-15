
object Etl {
  //  from Map(
  //  1 -> ("A", "E", "I", "O", "U", "L", "N", "R", "S", "T")
  // )
  // to Map(a -> 1, e -> 1...

  def transform(oldMap: Map[Int, Seq[String]]): Map[String, Int]= {

    val newMap: Map[String, Int] = Map.empty

    for {
      (k, v) <- oldMap
      letter <- v
    } yield {
      letter.toLowerCase -> k
    }

    // akshayd29's solution
//    object Etl {
//      def transform(input: Map[Int, Seq[String]]): Map[String, Int] = {
//        input.flatMap(x => x._2.map(y => (y.toLowerCase -> x._1)))
//      }
//    }

    /**
     * val newMap: Map[String, Int] = Map.empty
     * for ( (v -> k) <- oldMap.keys
     */
  }



}


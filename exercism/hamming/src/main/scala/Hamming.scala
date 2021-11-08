


object Hamming {
  def distance(a: String, b: String): Option[Int] = {

    if (a.length != b.length) None
    else {
      val strLen = a.length
      def doCount(acc: Int, c: Int): Int = {
        if (c >= strLen) acc
        else if (a(c) == b(c)) doCount(acc, c + 1)
        else doCount(acc + 1, c + 1)
      }
      Some(doCount(0, 0))
    }
  }



}



//object Hamming {
//  def distance(a: String, b: String): Option[Int] = {
//    if (a.length == b.length) {
//      val strandLen = a.length
//      def doCount(acc: Int, count: Int): Int = {
//        if (count >= strandLen) acc
//        else if (a(count) == b(count)) doCount(acc, count+1)
//        else doCount(acc +1, count +1 )
//      }
//      Some(doCount(0,0))
//    }
//    else None
//
//  }
//}



//llgruff's
//solution
//object Hamming {
//  def distance(a: String, b: String): Option[Int] = {
//    if (a.length != b.length) None
//    else Some(a.zip(b).count(r => r._1 != r._2))
//  }
//}
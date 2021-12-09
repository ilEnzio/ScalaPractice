package EssentialScalaBookEx.Ch05

object Pairs {
  // 5.4.1.1

  case class Pair[A, B](one: A, two: B)

  def main(args: Array[String]): Unit = {

    val pair = Pair[String, Int]("hi", 2)
    // pair: Pair[String,Int] = Pair(hi,2)

    println(pair.one)
    // res0: String = hi

    println(pair.two)
    // res1: Int = 2

  }

}

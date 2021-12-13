package EssentialScalaBookEx.Ch05

object MaybeMistake {

  sealed trait Maybe[A]{
    def fold[B](empty: B)(f: A => B): B = this match {
      case Empty() => empty
      case Full(v) => f(v)
    }
  }

  case class Empty[A]() extends Maybe[A]
  case class Full[A](value: A) extends Maybe[A]


  def main(args: Array[String]): Unit = {
    val perhaps: Maybe[Int] = Empty[Int]
    val perhaps2: Maybe[Int] = Full(1)

    println((perhaps))
    println(perhaps2)
    println(perhaps2.fold[Int](0){case x => x})  // solved but don't understand
  }
}
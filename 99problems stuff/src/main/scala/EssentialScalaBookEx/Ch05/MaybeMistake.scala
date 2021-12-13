package EssentialScalaBookEx.Ch05

object MaybeMistake {

  sealed trait Maybe[A]{
    def fold[B](empty: B)(f: A => B): B = this match {
      case Empty() => empty
      case Full(v) => f(v)
    }

    def flatMap[B](f: A => Maybe[B]): Maybe[B] = this match {
      case Empty() => Empty[B]()
      case Full(v) => f(v)
    }

    def map[B](f: A => B): Maybe[B] = this match {
      case Empty() => Empty[B]()
      case Full(v) => Full(f(v))
    }
// Don't understand; have to review "map in terms of flatmap"
    def map2[B](f: A=> B): Maybe[B] = {
      flatMap[B](v => Full(f(v)))
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
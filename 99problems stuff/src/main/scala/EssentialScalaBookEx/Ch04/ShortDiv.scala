package EssentialScalaBookEx.Ch04

import scala.concurrent.duration.Duration.Infinite

object ShortDiv {

  // 4.2.2.3

  sealed trait DivisionResult{}

  final case class Finite(x: Int) extends DivisionResult
  case object Infinite extends DivisionResult {}

  object Divide {
    def apply(x: Int, y: Int): DivisionResult = (x, y) match {
      case (_, 0) => Infinite
      case _ => Finite(x/y)
    }

  }

  def main(args: Array[String]): Unit = {

    val zeroDiv = Divide(3, 0)
    val valDiv = Divide(1, 2)
    val testFin = Finite(2)
    println(testFin)
    println(zeroDiv)
    println(valDiv)
  }

}

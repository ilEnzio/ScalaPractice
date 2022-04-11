package ScalaWithCats.Ch05


import cats.syntax.all.*
import cats.instances.future._ // for Monad
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

import scala.concurrent.duration._


object ExTransformRollOut {

  import cats.data.EitherT

  type Response[A] = Future[Either[String, A]]
  type Response2[A] = EitherT[Future, String, A]
  // it gonna use and EitherT[Future, String, A]

//  def parseBattleMessage: Future[Either[String, Int]]

  def getPowerLevel(autobot: String): Response2[Int] = powerLevels.get(autobot) match {
    case None => EitherT.left(Future(s"$autobot is unreachable"))
    case Some(x) => EitherT.right(Future(x))

    }

  def canSpecialMove(ally1: String, ally2: String): Response2[Boolean] = {
    for {
      a <- getPowerLevel(ally1)
      b <- getPowerLevel(ally2)
    } yield (a + b) > 15


  }

  def tacticalReport(ally1: String, ally2: String): String = {
    val resultStack = canSpecialMove(ally1, ally2).value
//    result match {
//            case false => s"Insufficient Power!!"
//            case true => s"Transformers....Roll Out!"
//          }
    s"$resultStack"

    Await.result(resultStack, 1.second) match {
      case Left(msg) => s"Comms error: $msg"
      case Right(true) => s"$ally1 and $ally2 ... Transformers... roll out!"
      case Right(false) => s"$ally1 and $ally2... Insufficient Power..."
    }


  }

  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )


  def main(args: Array[String]): Unit = {
    println(tacticalReport("Jazz", "Kelly"))
    println(getPowerLevel("Jazz"))
  }
}

package ScalaWithCats.Ch05

import cats.data.OptionT
import cats._
import cats.syntax.all._
import cats.instances.list._     // for Monad
//import cats.syntax.applicative._ // for pure


object MonadTransformers {


//  def lookupUserName(id: Long): Either[Error, Option[String]] =
//    for {
//      optUser <- lookupUser(id)
//    } yield {
//      for { user <- optUser } yield user.name
//    }

  type ListOption[A] = OptionT[List, A]

  val result1: ListOption[Int] = OptionT(List(Option(10)))
  // result1: ListOption[Int] = OptionT(List(Some(10)))

  val result2: ListOption[Int] = 32.pure[ListOption]
  // result2: ListOption[Int] = OptionT(List(Some(32)))


  def main(args: Array[String]): Unit = {


    println(result1)

    println(result2)

    val test = result1.flatMap{ x => result2.map { y => x + y}}
    println(test)
//    println(result1 + result2)
  }
}

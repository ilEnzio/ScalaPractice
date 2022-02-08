package ScalaWithCats.Ch02


import cats._
import cats.implicits._
import cats.instances.string._ // for Monoid
//import cats.syntax.semigroup._ // for |+|

object MonoidSyntax {
//
  val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
  // stringResult: String = "Hi there"
//
//  import cats.instances.int._ // for Monoid
//
  val intResult = 1 |+| 2 |+| Monoid[Int].empty
  // intResult: Int = 3


  def main(args: Array[String]): Unit = {
//
    println(stringResult)
    println(intResult)
  }
}

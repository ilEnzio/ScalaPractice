package ScalaWithCats.Ch01

import cats.Show
import cats.instances.int._    // for Show
import cats.instances.string._ // for Show
import cats.syntax.show._ // for show

object MeetCats {

  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]



  final case class Cat(name: String, age: Int, color: String)

  object Cat {
    implicit val catShow: Show[Cat] = {
      Show.show[Cat] { c =>
        s"${c.name} is a ${c.age} year-old ${c.color} cat."
      }
    }
  }


    def main(args: Array[String]): Unit = {
      val intAsString: String =
        showInt.show(123)
      // intAsString: String = "123"

      val stringAsString: String =
        showString.show("abc")
      // stringAsString: String = "abc"

      println(intAsString)
      println((stringAsString))

      println(124.show)
      println("erlr".show)
      val eCat = Cat("cool cat", 50, "black and gray")
      println(eCat.show)

    }

}

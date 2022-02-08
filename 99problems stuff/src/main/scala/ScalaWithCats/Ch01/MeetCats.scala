package ScalaWithCats.Ch01

import cats.Show
import cats.instances.int._    // for Show
import cats.instances.string._ // for Show
import cats.syntax.show._ // for show

//import cats._  - imports all of Cats’ type classes in one go;
//import cats.implicits._  imports all of the standard type class
// instances and all of the syntax in one go.

object MeetCats {

  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]



  final case class Cat(name: String, age: Int, color: String)

  object Cat {
    implicit val catShow: Show[Cat] = {
      Show.show[Cat] { c =>
        s"${c.name} is a ${c.age} year-old ${c.color} cat." // this is using the string not show!
        //        show"${c.name} is a ${c.age} year-old ${c.color} cat." // show interpolator
      }
    }

    // book solution, I believe used the show for string within the
    // show for Cat:
    //    implicit val catShow: Show[Cat] = Show.show[Cat] { cat =>
    //      val name  = cat.name.show
    //      val age   = cat.age.show
    //      val color = cat.color.show
    //      s"$name is a $age year-old $color cat."
    //    }


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
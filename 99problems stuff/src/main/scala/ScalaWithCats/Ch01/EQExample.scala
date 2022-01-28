package ScalaWithCats.Ch01

import cats.Eq

import cats.instances.int._ // for EQ

import cats.syntax.eq._ // for === and =!=
import cats.instances.int._    // for Eq
import cats.instances.option._ // for Eq

object EQExample {

  val eqInt = Eq[Int]


  final case class Cat(name: String, age: Int, color: String)

  implicit val catEq: Eq[Cat] = {
    Eq.instance[Cat]{ (cat1, cat2) =>
      cat1.name === cat2.name &&
        cat1.age === cat2.age &&
        cat1.color === cat2.color
    }
  }


  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]



  def main(args: Array[String]): Unit = {

    println((eqInt.eqv(123, 123)))
    println(eqInt.eqv(123, 234))
//    println(eqInt.eqv(123, "test"))  error

    println(123 === 123)
    println(123 =!= 234)

    println((Some(1): Option[Int]) === (None : Option[Int]))
    println(Option(1) === Option.empty[Int])

    import cats.syntax.option._ // for some and none

    1.some === none[Int]
    // res10: Boolean = false
    1.some =!= none[Int]
    // res11: Boolean = true

    println(s"Cat1 === Cat2 ?? ${cat1 === cat2}")
    println(s"OCat1 === OCat2 ?? ${optionCat1 === optionCat2}")
  }

}

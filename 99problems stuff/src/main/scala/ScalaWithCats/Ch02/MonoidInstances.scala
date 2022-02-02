package ScalaWithCats.Ch02

object MonoidInstances {


  import cats.Monoid
  import cats.instances.string._ // for Monoid

  Monoid[String].combine("Hi ", "there")
  // res0: String = "Hi there"
  Monoid[String].empty
  // res1: String = ""

  import cats.Semigroup

  Semigroup[String].combine("Hi ", "there")
  // res4: String = "Hi there"


  import cats.instances.int._    // for Monoid
  import cats.instances.option._ // for Monoid

  val a = Option(22)
  // a: Option[Int] = Some(22)
  val b = Option(20)
  // b: Option[Int] = Some(20)

  Monoid[Option[Int]].combine(a, b)
  // res6: Option[Int] = Some(42)



  def main(args: Array[String]): Unit = {
    println(Monoid[String].combine("Erlr", "G"))
    println(Semigroup[String].combine("erle", "g"))


    println(Monoid[Option[Int]].combine(a, b))
  }
}

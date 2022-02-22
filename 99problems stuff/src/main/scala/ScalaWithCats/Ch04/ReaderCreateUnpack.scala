package ScalaWithCats.Ch04

object ReaderCreateUnpack {

//  We can create a Reader[A, B] from a function A => B using
//    the Reader.apply constructor:

  import cats.data.Reader

  final case class Cat(name: String, favoriteFood: String)

  val catName: Reader[Cat, String] =
    Reader(cat => cat.name)
  // catName: Reader[Cat, String] = Kleisli(<function1>)
//  We can extract the function again using the Reader's
//  run method and call it using apply as usual:

    println(catName.run(Cat("Garfield", "lasagne")))
  // res1: cats.package.Id[String] = "Garfield"

  def main(args: Array[String]): Unit = {

  }

}

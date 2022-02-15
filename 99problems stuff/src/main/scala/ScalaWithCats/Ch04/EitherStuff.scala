package ScalaWithCats.Ch04

//import cats.*
import cats.syntax.all._

object EitherStuff {


  def countPositive(nums: List[Int]) =
    nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
      if(num > 0) {
        accumulator.map(_ + 1)
      } else {
        Left("Negative. Stopping!")
      }
    }


  def main(args: Array[String]): Unit = {


    val either1: Either[String, Int] = Right(10)
    val either2: Either[String, Int] = Right(32)
    val test = for {
      a <- either1
      b <- either2
    } yield a + b
    // res1: Either[String, Int] = Right(42)

    println(test)
    println(countPositive(List(1, 2, 3)))
    println(countPositive(List(1, -2, 3)))

//    cats.syntax.either adds some useful extension methods to the Either companion object.
//    The catchOnly and catchNonFatal methods are great for capturing Exceptions as instances of Either:

      println(Either.catchOnly[NumberFormatException]("foo".toInt))
    // res7: Either[NumberFormatException, Int] = Left(
    //   java.lang.NumberFormatException: For input string: "foo"
    // )
    println(Either.catchOnly[NumberFormatException]("32".toInt))


    println(Either.catchNonFatal(sys.error("Badness")))
    // res8: Either[Throwable, Nothing] = Left(java.lang.RuntimeException: Badness)


//    Transforming Eithers


  }

}

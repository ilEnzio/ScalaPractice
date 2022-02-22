package ScalaWithCats.Ch04

object WriterMonadEx {

//  slowly helper function ensures this takes a while to run,
//  even on the very small examples below:



  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Int = {
    val ans = slowly(if(n == 0) 1 else n * factorial(n - 1))
    println(s"fact $n $ans")
    ans


  }
  import cats.data.Writer
  import cats.instances.vector._   // for Monoid
  import cats.syntax.applicative._ // for pure
  import cats.syntax.writer._ // for tell


  // book solution

  type Logged[A] = Writer[Vector[String], A]

  def factorial2(n: Int): Logged[Int] =
    for {
      ans <- if(n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial2(n - 1).map(_ * n))
      }
      _   <- Vector(s"fact $n $ans").tell
    } yield ans

  val (log, res) = factorial2(5).run

  println(log, res)

  def main(args: Array[String]): Unit = {
    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits._
    import scala.concurrent.duration._

//    Await.result(Future.sequence(Vector(
//      Future(factorial(5)),
//      Future(factorial(5))
//    )), 5.seconds)


    Await.result(Future.sequence(Vector(
      Future(factorial2(5)),
      Future(factorial2(5))
    )).map(_.map(_.written)), 5.seconds)
//    println(factorial(5))
  }


}

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


  import scala.concurrent.*
  import scala.concurrent.ExecutionContext.Implicits.*
  import scala.concurrent.duration.*

  Await.result(Future.sequence(Vector(
    Future(factorial(5)),
    Future(factorial(5))
  )), 5.seconds)


  def main(args: Array[String]): Unit = {
//    println(factorial(5))
  }


}

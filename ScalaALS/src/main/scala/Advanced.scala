import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App {
  // lazy evaluation
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  // "pseudo-collection": Option, Try
  def methodWhichCanReturnNull() : String = "hello, Scala"
  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala"
  // option = "collection" which at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have received a valid string: $string"
    case None => "I obtained nothing"
   }

  // Try guards against methods that can throw exceptions (unsafe methods)

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Failure(exception) => s"I have obtained and exception: $exception"
    case Success(value) => s"I have obtained a valid string: $value"
  }

  /**
   * Evaluate something on another thread
   * (asynchronous programming)
   */

  val aFuture = Future { // Future.apply
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }

  // future is a "collection which contains a value when it's evaluated
  // future is composable with map, flatmap, and filter
  // future, try, and option are called Monads


  Thread.sleep(2000)


  /**
   * Implicits basics
   */

  // #1: implicit arguments
  def aMethodWithIMplicitArgs(implicit arg: Int) = arg +1 // rather than an int, think of using a monoid/semi-group, etc
  implicit val myImplicit = 46
  println(aMethodWithIMplicitArgs)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }

  println(23.isEven()) // new MyRichInteger(23).isEven()
  // use carefully

}

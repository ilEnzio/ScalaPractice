package ScalaWithCats.Ch04

object CreateUnpackStateMonad {

  import cats.data.State

  val a = State[Int, String]{ state =>
    (state, s"The state is $state")
  }

//  In other words, an instance of State is a function that does two things:
//    transforms an input state to an output state;
//  computes a result.

  val step1 = State[Int, String]{ num =>
    val ans = num + 1
    (ans, s"Result of step1: $ans")
  }

  val step2 = State[Int, String]{ num =>
    val ans = num * 2
    (ans, s"Result of step2: $ans")
  }

  val both = for {
    a <- step1
    b <- step2
  } yield (a, b)

  val (state2, result2) = both.run(20).value
  // state: Int = 42
  // result: (String, String) = ("Result of step1: 21", "Result of step2: 42")


  def main(args: Array[String]): Unit = {

    // Get the state and the result:
    val (state, result) = a.run(10).value
    // state: Int = 10
    // result: String = "The state is 10"

    // Get the state, ignore the result:
    val justTheState = a.runS(10).value
    // justTheState: Int = 10

    // Get the result, ignore the state:
    val justTheResult = a.runA(10).value
    // justTheResult: String = "The state is 10"

    println(state, result)

    println(justTheState)
    println((justTheResult))

    println(state2, result2)

  }


}

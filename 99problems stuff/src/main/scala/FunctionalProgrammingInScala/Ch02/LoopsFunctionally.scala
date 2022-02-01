package FunctionalProgrammingInScala.Ch02

object LoopsFunctionally {

  def factorial(n: Int): Int = {
    def go(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else go(n-1, n*acc)
    }
    go(n, 1)
  }

  def fib(n: Int): Int = {
    def go(n: Int, acc1: Int, acc2: Int): Int = { n match {
      case 0 => acc1
      case _ => go(n-1, acc2, acc1 + acc2)
      }
    }
    go(n, 0, 1)
  }

  def abs(n: Int): Int = { n match {
    case n if (n < 0) =>  -n
    case _ => n
    }
  }

  private def formatAbs(x: Int) = {
    val msg = "The absolute value of %d is %d."
    msg.format(x, abs(x))
  }

  private def formatFactorial(n: Int) = {
    val msg = "the factorial of %d is %d."
    msg.format(n, factorial(n))
  }

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg ="The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  def main(args: Array[String]): Unit = {
    println(fib(5))


    println(formatAbs(-42))
    println(formatFactorial(7))
    println(formatResult("abs value", -42, abs))
    println(formatResult("factorial", 7, factorial))

  }
}

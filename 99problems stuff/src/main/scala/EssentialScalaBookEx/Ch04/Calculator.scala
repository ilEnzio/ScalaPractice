package EssentialScalaBookEx.Ch04

object Calculator {

  sealed trait CalcResult
  case class Success(value: Int) extends CalcResult
  case class Fail(value: String) extends CalcResult

  object Calculate {
    def apply(): CalcResult = ???
    def +(calcResult: CalcResult, x: Int): CalcResult = calcResult match {
      case Success(a) => Success(a + x)
      case Fail(x) => Fail(x)
    }

    def -(calcResult: CalcResult, x: Int): CalcResult = calcResult match {
      case Success(a) => Success(a - x)
      case Fail(x) => Fail(x)
    }

    def /(calcResult: CalcResult, divisor: Int): CalcResult = (calcResult,divisor) match {
      case (Fail(x), _) => Fail(x)
      case (_, 0) => Fail("Division by zero")
      case (Success(x), y) => Success(x/y)
    }

    // this way avoids using a tuple. it splits the Successful case vs Fails.
    def /-/(calc: CalcResult, operand: Int): CalcResult =
      calc match {
        case Success(result) =>
          operand match {
            case 0 => Fail("Division by zero")
            case _ => Success(result / operand)
          }
        case Fail(reason) => Fail(reason)
      }

  }

  def main(args: Array[String]): Unit = {
    assert(Calculate.+(Success(1), 1) == Success(2))

    println(Calculate.+(Success(1),1))

    assert(Calculate.-(Success(1), 1) == Success(0))
    assert(Calculate.+(Fail("Badness"), 1) == Fail("Badness"))

    // test Division
    assert(Calculate./(Success(4), 2) == Success(2))
    assert(Calculate./(Success(4), 0) == Fail("Division by zero"))
    assert(Calculate./(Fail("Badness"), 0) == Fail("Badness"))
  }

}

package EssentialScalaBookEx.Ch04

object Calculator {

  sealed trait CalcResult
  final case class Success(value: Double) extends CalcResult
  final case class Fail(value: String) extends CalcResult

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



  sealed trait Expression {
    def eval: CalcResult =
      this match {
        case Addition(l, r) =>
          l.eval match {
            case Fail(reason) => Fail(reason)
            case Success(r1) =>
              r.eval match {
                case Fail(reason) => Fail(reason)
                case Success(r2) => Success(r1 + r2)
              }
          }
        case Subtraction(l, r) =>
          l.eval match {
            case Fail(reason) => Fail(reason)
            case Success(r1) =>
              r.eval match {
                case Fail(reason) => Fail(reason)
                case Success(r2) => Success(r1 - r2)
              }
          }
        case Division(l, r) =>
          l.eval match {
            case Fail(reason) => Fail(reason)
            case Success(r1) =>
              r.eval match {
                case Fail(reason) => Fail(reason)
                case Success(r2) =>
                  if(r2 == 0)
                    Fail("Division by zero")
                  else
                    Success(r1 / r2)
              }
          }
        case SquareRoot(v) =>
          v.eval match {
            case Success(r) =>
              if(r < 0)
                Fail("Square root of negative number")
              else
                Success(Math.sqrt(r))
            case Fail(reason) => Fail(reason)
          }
        case Number(v) => Success(v)
      }
  }
  final case class Addition(left: Expression, right: Expression) extends Expression
  final case class Subtraction(left: Expression, right: Expression) extends Expression
  final case class Division(left: Expression, right: Expression) extends Expression
  final case class SquareRoot(value: Expression) extends Expression
  final case class Number(value: Int) extends Expression


//  case class Addition(left: Expression, right: Expression) extends Expression
//  case class Subtraction(left: Expression, right: Expression) extends Expression
//  case class Division(left: Expression, right: Expression) extends Expression
//  case class SquareRoot(value: Double) extends Expression
//  case class Number(value: Double) extends Expression

  def main(args: Array[String]): Unit = {
    assert(Calculate.+(Success(1), 1) == Success(2))

    println(Calculate.+(Success(1),1))

    assert(Calculate.-(Success(1), 1) == Success(0))
    assert(Calculate.+(Fail("Badness"), 1) == Fail("Badness"))

    // test Division
    assert(Calculate./(Success(4), 2) == Success(2))
    assert(Calculate./(Success(4), 0) == Fail("Division by zero"))
    assert(Calculate./(Fail("Badness"), 0) == Fail("Badness"))

    // test eval
    val tNum1 = Number(28)
    val tNum2 = Number(11)
    val addTest = Addition(tNum1, tNum2).eval

    println(addTest)
  }
}

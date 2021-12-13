package EssentialScalaBookEx.Ch05

object CalculatorAgain {

  sealed trait Expression
//    def eval(ex: )

  final case class Addition(left: Expression, right: Expression) extends Expression
  final case class Subtraction(left: Expression, right: Expression) extends Expression
  final case class Division(left: Expression, right: Expression) extends Expression
  final case class SquareRoot(value: Expression) extends Expression
  final case class Number(value: Int) extends Expression

//
//  sealed trait Expression {
//    def eval: Sum[String, Double] =
//      this match {
//        case Addition(l, r) => lift2(l, r, (left, right) => Success(left + right))
//        case Subtraction(l, r) => lift2(l, r, (left, right) => Success(left - right))
//        case Division(l, r) => lift2(l, r, (left, right) =>
//          if(right == 0)
//            Failure("Division by zero")
//          else
//            Success(left / right)
//        )
//        case SquareRoot(v) =>
//          v.eval flatMap { value =>
//            if(value < 0)
//              Failure("Square root of negative number")
//            else
//              Success(Math.sqrt(value))
//          }
//        case Number(v) => Success(v)
//      }
//
//    def lift2(l: Expression, r: Expression, f: (Double, Double) => Sum[String, Double]) =
//      l.eval.flatMap { left =>
//        r.eval.flatMap { right =>
//          f(left, right)
//        }
//      }
//  }
//  final case class Addition(left: Expression, right: Expression) extends Expression
//  final case class Subtraction(left: Expression, right: Expression) extends Expression
//  final case class Division(left: Expression, right: Expression) extends Expression
//  final case class SquareRoot(value: Expression) extends Expression
//  final case class Number(value: Int) extends Expression


}

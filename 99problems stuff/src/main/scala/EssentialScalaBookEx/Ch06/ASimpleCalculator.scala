package EssentialScalaBookEx.Ch06
import scala.util.Try

object ASimpleCalculator {


  def calculator(a: String, op: String, b: String): Unit = {

    val oper: Int => Int => Int = op match {
      case "+" => x => y => x + y
      case "-" => x => y => x - y
      case "*" => x => y => x * y
      case "/" => x => y => x / y
    }

    for {
     aa <- a.toIntOption
     bb <- b.toIntOption
    } yield { (op, aa, bb) match {
      case ("/", _, 0) => println("Gen Err Message")
      case _ => println(oper(aa)(bb))
    }


//      oper(Some(aa))(Some(bb)) match {
//        case x => println(x)
//        case _ => println("Generic Error message")
//        }

      }
    }


//println(aa)
//if (aa == None || bb == None) println("Generic error message :P")
//else op match {
//  case "+" => println(s"$a + $b = ${aa + bb}")
//  case "-" => println(s"$a - $b = ${aa - bb}")
//  case "*" => println(s"$a - $b = ${aa * bb}")
//  case "/" if (b == "0") => println("Generic error message :P")
//  case "/" => println(s"$a - $b = ${aa / bb}")

  def main(args: Array[String]): Unit = {

    println(calculator("32", "/", "3"))
    println(calculator("Erle", "/", "3"))
    println(calculator("10", "/", "5"))
    println(calculator("10", "/", "0"))


  }





}

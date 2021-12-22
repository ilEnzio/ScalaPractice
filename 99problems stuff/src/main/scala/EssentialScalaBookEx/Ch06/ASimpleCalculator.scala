package EssentialScalaBookEx.Ch06

object ASimpleCalculator {

  // TODO basically I need to reread this whole section

  def calculator(a: String, op: String, b: String): Unit = {

    val oper: Int => Int => Option[Int] = op match {  // TODO Not necessary to curry; repeating the x, y
      case "+" => x => y => Some(x + y)  //
      case "-" => x => y => Some(x - y)
      case "*" => x => y => Some(x * y)
      case "/" => x => y => shortDiv(x, y) // didn't understand I could use this
      case _ => _ => _ => None
    }

    val test = for {
      aa <- a.toIntOption
      bb <- b.toIntOption
      c <- oper(aa)(bb)
    } yield c

    test match {
      case Some(x) => println(x)
      case None => println(s"Error $a $op $b")
    }
  }

  def calculator_v3(a: String, op: String, b: String): Unit = {
//    (a.toIntOption).flatMap((x:String) => b.flatMap(y => op.map(z => z(x)(y))))
//    println(a.toIntOption)
    val oper: Int => Int => Option[Int] = op match {
      case "+" => x => y => Some(x + y)  //
      case "-" => x => y => Some(x - y)
      case "*" => x => y => Some(x * y)
      case "/" => x => y => shortDiv(x, y) // didn't understand I could use this
      case _ => _ => _ => None
    }

    val res = a.toIntOption.flatMap(x =>
      b.toIntOption.map(y =>
        oper(x)(y)))

    res match {
      case Some(x) => println(x)
      case None => println(s"Error $a $op $b")
    }
  }



  /// book solution
  def readInt(str: String): Option[Int] =
    if(str matches "-?\\d+") Some(str.toInt) else None

  def shortDiv(a: Int, b: Int): Option[Int] =
    if (b == 0) None else Some(a/b)

  def calculator2(operand1: String, operator: String, operand2: String): Unit = {
    val result = for {
      a   <- readInt(operand1)
      b   <- readInt(operand2)
      ans <- operator match {
        case "+" => Some(a + b)
        case "-" => Some(a - b)
        case "*" => Some(a * b)
        case "/" => shortDiv(a, b)
        case _   => None
      }
    } yield ans

    result match {
      case Some(number) => println(s"The answer is $number!")
      case None         => println(s"Error calculating $operand1 $operator $operand2")
    }
  }

  def main(args: Array[String]): Unit = {

//    calculator("32", "/", "3")
//    calculator("Erle", "/", "3")
//    calculator("10", "/", "5")
//    calculator("10", "/", "0")
  calculator_v3("3", "2", "2")
        calculator_v3("32", "/", "3")
        calculator_v3("Erle", "/", "3")
        calculator_v3("10", "/", "0")
        calculator_v3("10", "/", "5")




  }





}
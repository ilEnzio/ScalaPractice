package ScalaWithCats.Ch04

object PostOrderCalc {


  import cats.data.State

  type CalcState[A] = State[List[Int], A]




  def evalOne(sym: String): CalcState[Int] = {

    def operator(func: (Int, Int) => Int): CalcState[Int] =
      State[List[Int], Int] {
        case b :: a :: tail =>
          val ans = func(a, b)
          (ans :: tail, ans)
        case _ => sys.error("Fail!")
      }

    def operand(num: Int): CalcState[Int] =
      State[List[Int], Int] { statck =>
        (num :: statck, num)
      }

    sym match {
      case "+" => operator((x, y) => x + y)
      case "-" => operator((x, y) => x - y)
      case "*" => operator((x, y) => x * y)
      case "/" => operator((x, y) => x / y)
      case num => operand(num.toInt)
    }



  }



  def main(args: Array[String]): Unit = {

    println("test")
  }
}

package EssentialScalaBookEx.Ch04

object Calculator {

  sealed trait CalcResult
  case class Success(value: Int) extends CalcResult
  case class Fail(value: String) extends CalcResult

  object Calculate {
    def apply(): CalcResult = ???
  }

  def main(args: Array[String]): Unit = {

  }

}

package EssentialScalaBookEx.Ch02

object SquareDance {

  object Calc {
    def square(x: Double): Double = x * x
    def cube(x: Double): Double = x * square(x)
  }

  object Calc2 {
    // method overloading - use the same name; different signature
    def square(x: Double): Double = x * x
    def square(x: Int): Int = x * x

    def cube(x: Double): Double = x * square(x)
    def cube(x: Int): Int = x * square(x)
  }

  def main(args: Array[String]): Unit = {

    val anInt = 22
    val aDoub = 2.2



    println(Calc2.square(anInt))
    println(Calc2.square(aDoub))

    println(Calc2.cube(anInt))
    println(Calc2.cube(aDoub))

    // test cases
    assert(Calc2.square(2.0) == 4.0)
    assert(Calc2.square(3.0) == 9.0)
    assert(Calc2.square(-2.0) == 4.0)
    

  }

}
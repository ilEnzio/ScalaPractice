package EssentialScalaBookEx.Ch03

object SimpleCounter {

  class Counter(val n: Int){

    def count: Int = n

    def inc(k: Int = 1): Counter = new Counter(n + k)

    def dec(k: Int = 1): Counter = new Counter(n - k)

    def adjust(adder: Adder): Counter =
      new Counter(adder(n))

  }

  class Adder(amount: Int) {
    def apply(in: Int) = in + amount
  }

  def main(args: Array[String]): Unit = {

    val testCounter1 = new Counter(2)
    val testCounter2 = new Counter(11)

    assert(testCounter1.inc().count == new Counter(3).count)
    assert(testCounter1.dec().count == new Counter(1).count)
    assert(testCounter2.inc().inc().dec().dec().dec().count == new Counter(10).count)

    assert(testCounter1.inc(5).count == 7)
    assert(testCounter2.dec(2).count == 9)
    assert(testCounter2.inc(3).inc(2).dec(7).dec(2).dec().count == 6)

    val add5 = new Adder(5)

    val testCountWAdd5 = testCounter1.adjust(add5)

    assert(testCountWAdd5.count == 7)


  }

}

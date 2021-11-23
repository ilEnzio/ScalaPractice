package EssentialScalaBookEx.Ch03

object SimpleCounter {

  case class Counter(n: Int = 0){

    // since every operation returns a new instance;
    // does it matter if 'count' is a field or method?
    def count: Int = n
    def inc(k: Int = 1): Counter = copy(n + k)
    def dec(k: Int = 1): Counter = copy(n - k)

    def adjust(adder: Adder): Counter =
      copy(adder(n))

  }

  class Adder(amount: Int) {
    def apply(in: Int): Int = in + amount
  }

  def main(args: Array[String]): Unit = {

    val testCounter1 = Counter(2)
    val testCounter2 = Counter(11)

    assert(testCounter1.inc() == Counter(3))
    assert(testCounter1.dec().count == Counter(1).count)
    assert(testCounter2.inc().inc().dec().dec().dec().count == Counter(10).count)

    assert(testCounter1.inc(5).count == 7)
    assert(testCounter2.dec(2).count == 9)
    assert(testCounter2.inc(3).inc(2).dec(7).dec(2).dec().count == 6)

    val add5 = new Adder(5)

    val testCountWAdd5 = testCounter1.adjust(add5)

    assert(testCountWAdd5.count == 7)


  }

}

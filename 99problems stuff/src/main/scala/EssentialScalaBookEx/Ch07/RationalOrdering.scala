package EssentialScalaBookEx.Ch07

object RationalOrdering {


  final case class Rational(numerator: Int, denominator: Int)


//  implicit val rationalOrdering = Ordering.fromLessThan[Rational]{(x, y) =>
//    (x.numerator.toDouble/x.denominator.toDouble) <
//      (y.numerator.toDouble/y.denominator.toDouble)}

  object Rational {
    implicit val ordering = Ordering.fromLessThan[Rational] { (x, y) =>
      (x.numerator.toDouble / x.denominator.toDouble) <
        (y.numerator.toDouble / y.denominator.toDouble)
    }
  }

  object Example {

    implicit val higherPriorityImplicit = Ordering.fromLessThan[Rational]((x, y) =>
      (x.numerator.toDouble / x.denominator.toDouble) >
        (y.numerator.toDouble / y.denominator.toDouble)
    )

    def example() = {
      assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
        List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
    }
  }



  def main(args: Array[String]): Unit = {


//    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
//      List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

  }

}

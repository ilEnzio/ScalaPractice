package ScalaWithCats.Ch02

import TruthAboutMonoids._

object AllSetMonoids {

  // what monoids and semigroups are there for sets?
  // combine: union
  // empty: nil
  implicit def setUnionMonoid[A]: Monoid[Set[A]] = {
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] = a union b
      def empty =  Set.empty[A]
    }
  }


  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      def combine(a: Set[A], b: Set[A]) =
        a intersect b
    }
//
//  implicit def symDiffMonoid[A]: Monoid[Set[A]] =
//    new Monoid[Set[A]] {
//      def combine(a: Set[A], b: Set[A]): Set[A] =
//        (a diff b) union (b diff a)
//      def empty: Set[A] = Set.empty
//    }


  def main(args: Array[String]): Unit = {
    val intSetMonoid = Monoid[Set[Int]]
    val strSetMonoid = Monoid[Set[String]]

    println(intSetMonoid.combine(Set(1, 2), Set(2, 3)))
    // res18: Set[Int] = Set(1, 2, 3)
    strSetMonoid.combine(Set("A", "B"), Set("B", "C"))
    // res19: Set[String] = Set("A", "B", "C")
  }
}

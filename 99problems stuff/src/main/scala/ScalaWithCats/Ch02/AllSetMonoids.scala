package ScalaWithCats.Ch02

import TruthAboutMonoids._
import scala.collection.immutable
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
}

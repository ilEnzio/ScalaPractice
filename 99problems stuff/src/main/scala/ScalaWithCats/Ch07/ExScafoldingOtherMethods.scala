package ScalaWithCats.Ch07

import cats.Monoid

object ExScafoldingOtherMethods {

//  Prove this to yourself by implementing substitutes for List's map,
//  flatMap, filter, and sum methods in terms of foldRight.

  def map[A, B](l: List[A])(f: A => B): List[B] = {
    l.foldRight(List.empty[B]){case (v, s) => f(v) :: s}
  }

  def flapMap[A, B](l: List[A])(f: A => List[B]): List[B] = {
    l.foldRight(List.empty[B]){case (v, s) => f(v) ++ s}
  }

  def filter[A](l: List[A])(p: A => Boolean): List[A] = {
    l.foldRight(List.empty[A]){ case (v, s) =>
      if (p(v)) v :: s
      else s
    }
  }


//  def sum[A: Monoid[A]](l: List[A]): A = {
//    l.foldRight(List.empty[A]){ case (v, s) => v |+| s}
//  }

  import cats.Monoid

  def sumWithMonoid[A](list: List[A])
                      (implicit monoid: Monoid[A]): A =
    list.foldRight(monoid.empty)(monoid.combine)





}

package ScalaWithCats.Ch04

import cats.Eval

object SaferFoldEval {

//  The naive implementation of foldRight below is not stack safe.
//  Make it so using Eval:

  def efoldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, efoldRight(tail, acc)(fn))
      case Nil =>
        acc
    }


  // book solution
//  The easiest way to fix this is to introduce a helper method
//  called foldRightEval. This is essentially our original method
//  with every occurrence of B replaced with Eval[B], and a call to
//    Eval.defer to protect the recursive call:

  import cats.Eval

  def foldRightEval[A, B](as: List[A], acc: Eval[B])
                         (fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

//  We can redefine foldRight simply in terms of foldRightEval
//  and the resulting method is stack safe:

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) =>
      b.map(fn(a, _))
    }.value

  foldRight((1 to 100000).toList, 0L)(_ + _)
  // res24: Long = 5000050000L

  def main(args: Array[String]): Unit = {

  }

}

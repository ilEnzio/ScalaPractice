import scala.runtime.Nothing$

object P20 {


//  P20 (*) Remove the Kth element from a list.
//  Return the list and the removed element in a Tuple. Elements are numbered from 0.
//
//  Example:
//
//    scala> removeAt(1, List('a, 'b, 'c, 'd))
//
//  res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  def removeAt[A](k: Int, l: List[A]): (List[A], Option[A]) = {
    l.zipWithIndex.foldLeft(List[A](), None){ case (state, value) =>
      if (value._2 == k) (state._1, value._1) // don't know why this is happening
      else (value._1 :: state._1, state._2)
      }
    }





  def main(args: Array[String]): Unit = {


    println(removeAt(1, List('a, 'b, 'c, 'd)))
  }
}

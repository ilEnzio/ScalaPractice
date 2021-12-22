package Probs

object P20 {


  //  P20 (*) Remove the Kth element from a list.
  //  Return the list and the removed element in a Tuple. Elements are numbered from 0.
  //
  //  Example:
  //
  //    scala> removeAt(1, List('a, 'b, 'c, 'd))
  //
  //  res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  // does work because list is reversed! could use foldRight.
  def removeAt[A](k: Int, l: List[A]): (List[A], Option[A]) = {
    l.zipWithIndex.foldLeft(List[A](), None: Option[A]) { case (state, value) =>
      if (value._2 == k) (state._1, Some(value._1))
      else (value._1 :: state._1, state._2)
    }
  }
  // fold on the list starting from the right,
  // the initial state is empty list, None, the last index;
  // as we fold if our index value equals the target index then
  // put an Option of that value into our accumulator/state, otherwise
  // put that value in the list part of our state, put nothing in the "removed" part  (cr

  // FoldRight is not tail recursive!
  def removeAt_v3[A](k: Int, l: List[A]): (List[A], A) = {
    l.foldRight((List.empty[A], Option.empty[A], l.length - 1)) {
      case (e, (list, r, idx)) =>
        if (idx == k) (list, Some(e), idx - 1)
        else (e :: list, r, idx - 1)
    } match {
      case t => (t._1, t._2.get)
    } // I think there's alternate to .get?
  }

  // TODO: Q - is there a way to generalise over the collection structure?  maybe not because
  // I need to know how to build the structure as well? so is the cons operator implemented
  // on each collection in the the same way fold is?

  def removeAt_v4[A](k: Int, l: Seq[A]): (Seq[A], A) = {
    l.foldRight(List[A](), Option.empty[A], l.length - 1) {
      case (e, (list, r, idx)) =>
        if (idx == k) (list, Some(e), idx - 1)
        else (e +: list, r, idx - 1) // Does +: operator knows how to operate an any collection?
    } match {
      case t => (t._1, t._2.get)
    }
  }

  def main(args: Array[String]): Unit = {

    println(removeAt(1, List('a, 'b, 'c, 'd)))
    println(removeAt_v3(1, List('a, 'b, 'c, 'd)))
    println(removeAt_v4(1, List('a, 'b, 'c, 'd)))


  }
}

package EssentialScalaBookEx.Ch06

object Unique {

  // plan 1 - drop into set
  // plan 1.2 drop into map

  // plan 2 - use a second collection, add element to second collection, then
  // compare each element from the first collection to ALL elements in second collect, if
  // the element is not present in the second collection, then add it to that collection.

  // plan 3 - sort then compare NO Good

  // plan 4 - contains - remove element from list; check to see if the list now contains this element, if
  // it does contain the element, then discard this element and grab the next element in the list.  if
  // it doesn't not contain the element, then add the element to the second collection.

  def onlyUniqueFrom[A](l: Seq[A]): Seq[A] = {

    l.foldLeft(Seq.empty[A]){ (state, value) =>
      if (state.contains(value)) state
      else value +: state
      // b +: mid :+ e
    }
  }

  def main(args: Array[String]): Unit = {

    val test01 = Seq(2, 3, 7, 6, 2, 7 )
    println(onlyUniqueFrom(test01))



  }

}
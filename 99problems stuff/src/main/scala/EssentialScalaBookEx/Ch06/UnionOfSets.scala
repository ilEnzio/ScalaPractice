package EssentialScalaBookEx.Ch06

object UnionOfSets {

  //  Write a method that takes two sets and returns a set containing the union of the elements. Use iteration,
  //  like map or foldLeft, not the built-in union method to do so!

  def concatSet[A](s1: Set[A], s2: Set[A]): Set[A] =
    s1.foldLeft(s2) { case (state, value) =>
      state + value
    }


  def main(args: Array[String]): Unit = {


    val testSet = Set(2,3, 4)
    val testSet02 = Set( 4, 8, 8, 2)

    println(concatSet(testSet, testSet02))
  }


}
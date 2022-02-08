package ScalaWithCats.Ch03

object ExamplesOfFunctors {

  List(1, 2, 3).map(n => n + 1)
  // res0: List[Int] = List(2, 3, 4)



  // Think of map as a way of sequencing computations on values ignoring
  // some complication dictated by the relevant data type:
  List(1, 2, 3).
    map(n => n + 1).
    map(n => n * 2).
    map(n => s"${n}!")
  // res1: List[String] = List("4!", "6!", "8!")

}

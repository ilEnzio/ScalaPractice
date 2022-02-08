package ScalaWithCats.Ch03

object FunctorSyntax {

  import cats.Functor
  import cats.instances.function._ // for Functor
  import cats.syntax.functor._     // for map



  // mapping over functions. Scala’s Function1 type doesn’t have a map method
  // (it’s called andThen instead) so there are no naming conflicts:
  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 = (a: Int) => s"${a}!"
  val func4 = func1.map(func2).map(func3)

  func4(123)
  // res3: String = "248!"


  import cats.instances.option._ // for Functor
  import cats.instances.list._   // for Functor

  def doMath[F[_]](start: F[Int]) (implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)



  doMath(Option(20))
  // res4: Option[Int] = Some(22)
  doMath(List(1, 2, 3))
  // res5: List[Int] = List(3, 4, 5)


  def main(args: Array[String]): Unit = {
    println(doMath(List(1,2,3)))
  }


}

package ScalaWithCats.Ch04

object ComposingTransformingState {

  import cats.data.State
//  get extracts the state as the result;
//  set updates the state and returns unit as the result;
//  pure ignores the state and returns a supplied result;
//  inspect extracts the state via a transformation function;
//  modify updates the state using an update function.

  val getDemo = State.get[Int]
  // getDemo: State[Int, Int] = cats.data.IndexedStateT@741518c8
  getDemo.run(10).value
  // res1: (Int, Int) = (10, 10)

  val setDemo = State.set[Int](30)
  // setDemo: State[Int, Unit] = cats.data.IndexedStateT@509fb0a
  setDemo.run(10).value
  // res2: (Int, Unit) = (30, ())

  val pureDemo = State.pure[Int, String]("Result")
  // pureDemo: State[Int, String] = cats.data.IndexedStateT@562ae0a8
  pureDemo.run(10).value
  // res3: (Int, String) = (10, "Result")

  val inspectDemo = State.inspect[Int, String](x => s"${x}!")
  // inspectDemo: State[Int, String] = cats.data.IndexedStateT@2dc6b50f
  inspectDemo.run(10).value
  // res4: (Int, String) = (10, "10!")

  val modifyDemo = State.modify[Int](_ + 1)
  // modifyDemo: State[Int, Unit] = cats.data.IndexedStateT@71c93b27
  modifyDemo.run(10).value
  // res5: (Int, Unit) = (11, ())


  def main(args: Array[String]): Unit = {
    import State._

    println(getDemo.run(10).value)
    println(inspectDemo.run(10).value)


    val program: State[Int, (Int, Int, Int)] = for {
      a <- get[Int]
      _ <- set[Int](a + 1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c)
    // program: State[Int, (Int, Int, Int)] = cats.data.IndexedStateT@3b525fbf

    val (state, result) = program.run(1).value
    // state: Int = 3
    // result: (Int, Int, Int) = (1, 2, 3000)

    println(state, result)

  }

}

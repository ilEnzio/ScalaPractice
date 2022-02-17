package ScalaWithCats.Ch04

object EvalModel {


  import cats.Eval

  val now = Eval.now(math.random + 1000)
  // now: Eval[Double] = Now(1000.020590704322)
  val always = Eval.always(math.random + 3000)
  // always: Eval[Double] = cats.Always@4d8ca6eb
  val later = Eval.later(math.random + 2000)
  // later: Eval[Double] = cats.Later@601dc0b2

  val x = Eval.now{
    println("Computing X")
    math.random
  }
  // Computing X
  // x: Eval[Double] = Now(0.681816469770503)

  println(x.value) // first access
  println(x.value) // second access

  val y = Eval.always{
    println("Computing Y")
    math.random
  }
  // y: Eval[Double] = cats.Always@414a351

  y.value // first access
  // Computing Y
  // res12: Double = 0.09982997820703643 // first access
  y.value // second access
  // Computing Y
  // res13: Double = 0.34240334819463436


  val z = Eval.later{
    println("Computing Z")
    math.random
  }
  // z: Eval[Double] = cats.Later@b0a344a

  z.value // first access
  // Computing Z
  // res14: Double = 0.3604236919233441 // first access
  z.value // second access
  // res15: Double = 0.3604236919233441


  val ans = for {
    a <- Eval.now{ println("Calculating A"); 40 }
    b <- Eval.always{ println("Calculating B"); 2 }
  } yield {
    println("Adding A and B")
    a + b
  }
  // Calculating A
  // ans: Eval[Int] = cats.Eval$$anon$4@2d0f2cbf

  println(ans.value) // first access
  // Calculating B
  // Adding A and B
  // res17: Int = 42 // first access
  println(ans.value) // second access
  // Calculating B
  // Adding A and B
  // res18: Int = 42


  // Memoize
  val saying = Eval
    .always{ println("Step 1"); "The cat" }
    .map{ str => println("Step 2"); s"$str sat on" }
    .memoize
    .map{ str => println("Step 3"); s"$str the mat" }
  // saying: Eval[String] = cats.Eval$$anon$4@ca01c64

  saying.value // first access
  // Step 1
  // Step 2
  // Step 3
  // res19: String = "The cat sat on the mat" // first access
  saying.value // second access
  // Step 3
  // res20: String = "The cat sat on the mat"

  def main(args: Array[String]): Unit = {
    println(now.value)
    // res6: Double = 1000.020590704322
    println(always.value)
    // res7: Double = 3000.97102818157
    println(later.value)
    // res8: Double = 2000.0126977436273




  }

}

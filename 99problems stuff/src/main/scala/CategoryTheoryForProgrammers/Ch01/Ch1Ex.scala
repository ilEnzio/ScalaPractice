package CategoryTheoryForProgrammers.Ch01

object Ch1Ex {

// Implement id

  def ident[A](x: A): A = x

  def compose[A, B, C](f1: A => B, f2: B => C): A => C = {
    f2.compose(f1)
  }

}

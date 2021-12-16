package EssentialScalaBookEx.Ch06

object Map {

  def map[A, B](l: Seq[A])( f: A => B): Seq[B] = {

    l.foldRight(Seq[B]()){(v,  s) => v match {
      case None => s
      case  _ => f(v) +: s
    }}
  }

  def main(args: Array[String]): Unit = {

    val test = Seq(4, 3, 2, 1)

    println(map(test){_ * 2 + "A"})

  }

}
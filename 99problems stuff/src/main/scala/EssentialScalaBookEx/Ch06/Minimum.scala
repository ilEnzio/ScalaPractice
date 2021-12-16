package EssentialScalaBookEx.Ch06

object Minimum {

  def min(l: Seq[Int]): Option[Int] = {
    l.foldLeft(l.headOption) { (state, value) =>
      if (state.get < value) state
      else Some(value)
    }
  }

  def main(args: Array[String]): Unit = {

    val test = Seq(4, 3, 2, 1)
    println(min(test))
  }

}
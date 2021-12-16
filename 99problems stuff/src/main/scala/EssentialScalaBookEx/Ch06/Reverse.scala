package EssentialScalaBookEx.Ch06

object Reverse {


  def reverse[A](l: Seq[A]): Seq[A] = {
    l.foldLeft(Seq.empty[A]){(s, v) =>
      if (v == None) s
      else v +: s
    }
  }

  def main(args: Array[String]): Unit = {

    val test = Seq(4, 3, 2, 1)
    println(reverse(test))

  }

}
package EssentialScalaBookEx.Ch06

object FoldLeft {


  def efoldLeft[A, B](l: Seq[A])(acc: B)(f: (A, B) => B): B = l match {
    case Seq() => acc
    case _ => efoldLeft(l.tail)(f(l.head, acc))(f)
  }

  def main(args: Array[String]): Unit = {

    val test = Seq(4, 3, 2, 1)
    println(efoldLeft(test)(0){_ + _})
  }
}
package EssentialScalaBookEx.Ch06

object FoldLeft {


  def efoldLeft[A, B](l: Seq[A])(acc: B)(f: (A, B) => B): B = l match {
    case Seq() => acc
    case _ => efoldLeft(l.tail)(f(l.head, acc))(f)
  }

  def efoldLeft2[A, B](l: Seq[A])(acc: B)(f: (A, B) => B): B = {
    var end = acc
    l.foreach(x => end = f(x,end))
    end
  }

  def main(args: Array[String]): Unit = {

    val test = Seq(4, 3, 2, 1)
    println(efoldLeft(test)(0){_ + _})
    println(efoldLeft2(test)(0){_ + _})
  }
}
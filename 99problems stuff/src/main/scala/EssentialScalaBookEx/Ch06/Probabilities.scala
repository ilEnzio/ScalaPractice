package EssentialScalaBookEx.Ch06

object Probabilities {

  final case class Distribution[A](pos: List[(A, Double)]){

//    def flatMap[B](f: A => Distribution[B]): Distribution[B] = {
//      pos.foldRight(List.empty[(A, B)]){ case (v, s) =>
//        v
//      }
//    }

    def map[B](f: A => B): Distribution[B] = {
      val newPos = pos.map{case (k, v) => f(k) -> v}
      Distribution(newPos)
    }

  }

  object Distribution {
    def uniform[A](l: List[A]): Distribution[A] = {
      val pb:Double = 100/l.length
      Distribution(l.map(x => (x -> pb)))
    }

  }


  def main(args: Array[String]): Unit = {
    val verbs = List("wrote", "chased", "slept on")

    println(Distribution.uniform(verbs))
  }

}

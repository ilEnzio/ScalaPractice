package EssentialScalaBookEx.Ch06

import scala.collection.Map

object Probabilities {

  final case class Distribution[A](events: List[(A, Double)]){

    def flatMap[B](f: A => Distribution[B]): Distribution[B] = {
      val newPos = events.flatMap{case (x, p) =>
        f(x).events.map{ case (z, p2) => z -> (p * p2)} }
      Distribution(newPos)
    }

     def flatMap_v2[B](f: A => Distribution[B]): Distribution[B] = {
      val newPos = for {
        (x, p) <- events
        (y, p2) <- f(x).events
      } yield y -> (p * p2)
      Distribution(newPos)
    }

    def map[B](f: A => B): Distribution[B] = {
      val newPos = events.map{case (k, v) => f(k) -> v}
      Distribution(newPos)
    }

  }

  object Distribution {
    def uniform[A](l: List[A]): Distribution[A] = {
      val pb: Double = 1.0 / l.length
      Distribution(l.map(x => (x -> pb)))
    }

    def weighted[A](l: List[(A, Double)]): Distribution[A] =
      Distribution(l)

  }


  def main(args: Array[String]): Unit = {


    val subM = Map(
      "Noel" -> Distribution.uniform(List("wrote", "chased", "slept on")),
      "The cat" -> Distribution.uniform(List("meowed at",  "chased", "slept on" )),
      "The dog" -> Distribution.uniform(List("barked at", "chased", "slept on")))

    val verbM = Map(
      "wrote" -> Distribution.uniform(List("the book", "the letter", "the code")),
      "chased" -> Distribution.uniform(List("the ball", "the dog", "the cat")),
      "slept on" -> Distribution.uniform(List("the bed", "the mat", "the train")),
      "meowed at" -> Distribution.uniform(List("Noel", "the door", "the food cupboard")),
      "barked at" -> Distribution.uniform(List("the postman", "the car", "the cat"))
    )


    val subjects = List("Noel", "The cat", "The dog")
    val verbs = List("wrote", "chased", "slept on")
    val objects = List("the book", "the ball", "the bed")


    val subDis = Distribution.uniform(subjects)
    val verbDis= Distribution.uniform(verbs)
    val objDis = Distribution.uniform(objects)

//    println(Distribution.uniform(verbs))

    def sentences_v2 = {
      for {
        (s, vl) <- subM
        (v, p) <- vl.events
        (o, p2) <- verbM(v).events
      } yield s"\"$s $v $o\" (${p*p2}%)\n"
    }

    println(sentences_v2)
    println(sentences_v2.size)

//    println(subDis)

  }

}

package EssentialScalaBookEx.Ch06

import scala.collection.Map

import EssentialScalaBookEx.Ch06.Probabilities._

object CatSmells {
//
//  Let’s create a more complex model. Imagine the following situation:
//
//    I put my food into the oven and after some time it ready to eat and produces
//  delicious smell with probability 0.3 and otherwise it is still raw and produces no
//    smell with probability 0.7. If there are delicious smells the cat comes to
//  harass me with probability 0.8, and otherwise it stays asleep. If there is no
//    smell the cat harasses me for the hell of it with probability 0.4 and otherwise
//  stays asleep.
//
//    Implement this model and answer the question: if the cat comes to harass me what
//  is the probability my food is producing delicious smells (and therefore is ready to eat.)






  def main(args: Array[String]): Unit = {


    val newDis: Distribution[String] = Distribution[String](List(("erle", .1)))
    val ovenDis: Map[String, Distribution[String]] = Map(
      "Oven" -> Distribution(List(("delicious smell", .3), ("raw; no smell", .7)))
    )

    val catHarDis: Map[String, Distribution[String]] = Map(
      "delicious smell" -> Distribution(List(("cat harass", .3), ("raw; no smell", .7)))
    )
//    val cat

    println(ovenDis)
  }

}

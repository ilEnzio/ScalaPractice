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

  sealed trait Food
  case object Raw extends Food
  case object Cooked extends Food

  val food: Distribution[Food] = Distribution.weighted(List((Cooked -> .3), (Raw -> .7)))

  sealed trait Cat
  case object Asleep extends Cat
  case object Harass extends Cat

  def cat(food: Food): Distribution[Cat] = food match {
    case Cooked => Distribution.weighted(List((Harass->.8),(Asleep->.2)))
    case Raw => Distribution.weighted(List((Harass->.4), (Asleep->.6)))
  }

  def main(args: Array[String]): Unit = {

    val ovenResult =
      for {
        fe <- food
        p <- cat(fe)
      } yield (fe, p)

    println(ovenResult)

    val harrassingCat = ovenResult.events
      .filter{case ((_, c), _) => c == Harass}

    println(harrassingCat)

    val probFoodCooked = {
      val cooked = harrassingCat(0)._2
      val total = harrassingCat.map{ case (_, p) => p}.sum
      f"${cooked/total*100}%2.0f" + "%"
    }

    println(probFoodCooked)
//
//    val newDis: Distribution[String] = Distribution[String](List(("erle", .1)))
//    val ovenDis: Map[String, Distribution[String]] = Map(
//      "Oven" -> Distribution(List(("delicious smell", .3), ("raw; no smell", .7)))
//    )
//
//    val catHarDis: Map[String, Distribution[String]] = Map(
//      "delicious smell" -> Distribution(List(("cat harass", .8), ("cat sleep", .2))),
//      "raw; no smell" -> Distribution(List(("cat harass", .4), ("cat sleep", .6)))
//    )
//
////    println(ovenDis)
//
//    def allProbs () = {
//      for {
//        (_, ol) <- ovenDis
//        (s, p) <- ol.events
//        (sm, p2) <- catHarDis(s).events
//      } yield s"$s $sm ${p * p2}"
//    }
//    println(allProbs())
//

  }

}

package EssentialScalaBookEx.Ch06

import scala.collection.Map

object UnionOfMaps {

  val ages = Map("Alice" -> 20,
    "Bob"     -> 30,
    "Charlie" -> 50,
    "Derek"   -> 40,
    "Edith"   -> 10,
    "Fred"    -> 60)

  val ages2 = Map("Enzio" -> 20,
    "Bob"     -> 30,
    "Erle" -> 50,
    "Derek"   -> 29,
    "Edith"   -> 10,
    "Fred"    -> 60)

  def unionMaps[A](m1: Map[A, Int], m2: Map[A, Int]): Map[A, Int] = {
    val fstMap = m1.map { case (a, b) =>
      if (m2.contains(a)) (a, b + m2(a))
      else (a, b)
    }

    val sndMap = m2.filter(x => !m1.contains(x._1))

    fstMap.foldLeft(sndMap)((s, v) =>
      s + v)
  }

  // book solution - I should have gotten this!
  def union[A](map1: Map[A, Int], map2: Map[A, Int]): Map[A, Int] = {
    map1.foldLeft(map2){ (map, elt) =>
      val (key, value1) = elt
      val value2 = map.get(key)
      val total = value1 + value2.getOrElse(0)
      map + (key -> total)
    }
  }



  def main(args: Array[String]): Unit = {

    println(unionMaps(ages, ages2))
    println(unionMaps(ages2, ages))
  }


}
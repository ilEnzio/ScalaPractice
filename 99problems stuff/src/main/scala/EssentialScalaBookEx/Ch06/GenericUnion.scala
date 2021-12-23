package EssentialScalaBookEx.Ch06

import scala.collection.Map

object GenericUnion {

//  def union[A](map1: Map[A, Int], map2: Map[A, Int]): Map[A, Int] = {
//    map1.foldLeft(map2){ (map, elt) =>
//      val (key, value1) = elt
//      val value2 = map.get(key)
//      val total = value1 + value2.getOrElse(0)
//      map + (key -> total)
//    }
//  }


//  There are many things that can be added, such as strings (string concatenation), sets (union),
//  and of course numbers. It would be nice if we could generalise our union method on
//  maps to handle anything for which a sensible add operation can be defined. How
//  can we go about doing this?


  def genUnionMaps[A, B](m1: Map[A, B], m2: Map[A, B], f: (B, B) => B): Map[A, B] = {
    val fstMap = m1.map { case (a, b) =>
      if (m2.contains(a)) (a, f(b, m2(a)))
      else (a, b)
    }

    val sndMap = m2.filter(x => !m1.contains(x._1))

    fstMap.foldLeft(sndMap)(_ + _)
  }

  // book solution:
    def union[A, B](map1: Map[A, B], map2: Map[A, B], f: (B, B)=> B): Map[A, B] = {
      map1.foldLeft(map2){ (map, elt) =>
        val (k, v) = elt
        val newV = map.get(k).map(v2 => f(v, v2)).getOrElse(v)
        map + (k -> newV)
      }
    }

  def main(args: Array[String]): Unit = {

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


    println(genUnionMaps[String, Int](ages, ages2, {_ + _}))
    println(genUnionMaps[String, Int](ages2, ages, {(x:Int , y: Int) => x + y}))
  }
}

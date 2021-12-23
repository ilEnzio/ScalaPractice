package EssentialScalaBookEx.Ch06

import collection.Map

object Favorites {

  val people = Set(
    "Alice",
    "Bob",
    "Charlie",
    "Derek",
    "Edith",
    "Fred")

  val ages = Map("Alice" -> 20,
    "Bob"     -> 30,
    "Charlie" -> 50,
    "Derek"   -> 40,
    "Edith"   -> 10,
    "Fred"    -> 60)

  val favoriteColors = Map(
    "Bob"     -> "green",
    "Derek"   -> "magenta",
    "Fred"    -> "yellow")

  val favoriteLolcats = Map(
    "Alice"   -> "Long Cat",
    "Charlie" -> "Ceiling Cat",
    "Edith"   -> "Cloud Cat")


  def getColor(s: String): String =
    favoriteColors.getOrElse(s, "beige")

  def displayFaves[A, B](l: Map[A, B]): Unit =
    for {
      (_, b) <- l
    } yield println(b)


  def lookup[A, B](m: Map[A, B], k: A): Option[B] =
    m.get(k)



  def main(args: Array[String]): Unit = {

    println(getColor("Erle"))
    println(getColor("Bob"))
    displayFaves(favoriteColors)

    println(lookup(favoriteLolcats, "Erle"))
    println(lookup(favoriteLolcats, "Edith"))

    val oldest = ages.maxBy{case (_, age) => age}
    println(getColor(oldest._1))


  }

}
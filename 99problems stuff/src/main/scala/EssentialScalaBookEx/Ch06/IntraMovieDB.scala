package EssentialScalaBookEx.Ch06

object IntraMovieDB {

  case class Film(
                   name: String,
                   yearOfRelease: Int,
                   imdbRating: Double)

  sealed trait FilmMaker
  case class Director(
                       firstName: String,
                       lastName: String,
                       yearOfBirth: Int,
                       films: Seq[Film])


  val memento           = new Film("Memento", 2000, 8.5)
  val darkKnight        = new Film("Dark Knight", 2008, 9.0)
  val inception         = new Film("Inception", 2010, 8.8)

  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven        = new Film("Unforgiven", 1992, 8.3)
  val granTorino        = new Film("Gran Torino", 2008, 8.2)
  val invictus          = new Film("Invictus", 2009, 7.4)

  val predator          = new Film("Predator", 1987, 7.9)
  val dieHard           = new Film("Die Hard", 1988, 8.3)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood = new Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

  val mcTiernan = new Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

  val nolan = new Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))

  val someGuy = new Director("Just", "Some Guy", 1990,
    Seq())

  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  // TODO: Write your code here!
  def numberOfFilms(n: Int): Seq[String] = {
    directors.filter(_.films.size > n).map(_.lastName)
  }

  def bornIn(year: Int): Director = {
    directors.find(_.yearOfBirth < year).getOrElse(Director("Nobody", "McNoone", 0, Nil))
  }

  def oldTimers(year: Int, n: Int): Seq[String] = {
    directors.filter(x => x.films.size > n && x.yearOfBirth < year).map(_.lastName)

  }
  // I like their solution better but I think it's less performant on large lists
  //def directorBornBeforeWithBackCatalogOfSize(year: Int, numberOfFilms: Int): Seq[Director] = {
  //  val byAge   = directors.filter(_.yearOfBirth < year)
  //  val byFilms = directors.filter(_.films.length > numberOfFilms)
  //  byAge.filter(byFilms.contains)
  //}


  def sortDirectors(ascending: Boolean = true): Seq[String] = {
    if (ascending) directors.sortWith((x,y) => x.yearOfBirth > y.yearOfBirth).map(_.lastName)
    else directors.sortWith((x,y) => y.yearOfBirth > x.yearOfBirth).map(_.lastName)
  }

  // book solution is slick!
  def directorsSortedByAge(ascending: Boolean = true) = {
    val comparator: (Director, Director) => Boolean =
      if(ascending) { (a, b) => a.yearOfBirth < b.yearOfBirth}
      else { (a, b) => a.yearOfBirth > b.yearOfBirth }

    directors.sortWith(comparator)
  }



  def main(args: Array[String]): Unit = {

    println(numberOfFilms(3))
    println(bornIn(1984))
    println(oldTimers(1960, 2))
    println(sortDirectors())
    println((sortDirectors(false)))

  }

}
package EssentialScalaBookEx.Ch03

object DirectorialDebut {

  class Director(
    val firstName: String,
    val lastName: String,
    val yearOfBirth: Int) {

    def name: String = s"$firstName $lastName"
    def copy(
      firstName: String = this.firstName,
      lastName: String = this.lastName,
      yearOfBirth: Int = this.yearOfBirth): Director =
      new Director(firstName, lastName, yearOfBirth)

    override def toString: String = s"$name (b. $yearOfBirth)"

  }

  class Film (val name: String, val yearOfRelease: Int,
              val imdbRating: Double, val director: Director) {

    def directorAge: Int = yearOfRelease - director.yearOfBirth
    def isDirectedBy(aDirector: Director) = director == aDirector
    def copy(
      name: String = this.name,
      yearOfRelease:Int = this.yearOfRelease,
      imdbRating: Double = this.imdbRating,
      director: Director = this.director): Film =
      new Film(name, yearOfRelease, imdbRating, director)

    override def toString: String = s"$name, $yearOfRelease, $imdbRating, by $director"

  }

  val eastwood          = new Director("Clint", "Eastwood", 1930)
  val mcTiernan         = new Director("John", "McTiernan", 1951)
  val nolan             = new Director("Christopher", "Nolan", 1970)
  val someBody          = new Director("Just", "Some Body", 1990)

  val memento           = new Film("Memento", 2000, 8.5, nolan)
  val darkKnight        = new Film("Dark Knight", 2008, 9.0, nolan)
  val inception         = new Film("Inception", 2010, 8.8, nolan)

  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
  val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
  val unforgiven        = new Film("Unforgiven", 1992, 8.3, eastwood)
  val granTorino        = new Film("Gran Torino", 2008, 8.2, eastwood)
  val invictus          = new Film("Invictus", 2009, 7.4, eastwood)

  val predator          = new Film("Predator", 1987, 7.9, mcTiernan)
  val dieHard           = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

  def main(args: Array[String]): Unit = {

    println(eastwood.yearOfBirth)
    println(dieHard.director.name)

    val testFilm = new Film("DieHarder", 1988, 8.3, mcTiernan)
    val testFilm2 = new Film("DieHard", 2018, 8.3, mcTiernan)

    println(dieHard)
    println(testFilm)
    println(dieHard.copy(name = "DieHarder") )
    assert(dieHard.copy(name = "DieHarder").name == testFilm.name)
    assert(dieHard.copy(yearOfRelease = 2018).yearOfRelease == testFilm2.yearOfRelease)

  }
}

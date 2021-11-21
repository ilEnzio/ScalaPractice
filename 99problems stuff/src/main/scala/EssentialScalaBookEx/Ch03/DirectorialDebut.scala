package EssentialScalaBookEx.Ch03

object DirectorialDebut {

  case class Director(
    firstName: String,
    lastName: String,
    yearOfBirth: Int) {

    def name: String = s"$firstName $lastName"

//    def copy(
//      firstName: String = this.firstName,
//      lastName: String = this.lastName,
//      yearOfBirth: Int = this.yearOfBirth): Director =
//      new Director(firstName, lastName, yearOfBirth)

//    override def toString: String = s"$name (b. $yearOfBirth)"

  }

  object Director{
    def apply(firstName: String, lastname: String, yearOfBirth: Int): Director =
      new Director(firstName, lastname, yearOfBirth)

    def older(d1: Director, d2: Director): Director =
      if (d1.yearOfBirth <= d2.yearOfBirth) d1 else d2
  }

  case class Film (name: String, val yearOfRelease: Int,
              imdbRating: Double, val director: Director) {

    def directorAge: Int = yearOfRelease - director.yearOfBirth
    def isDirectedBy(aDirector: Director) = director == aDirector


//    def copy(
//      name: String = this.name,
//      yearOfRelease:Int = this.yearOfRelease,
//      imdbRating: Double = this.imdbRating,
//      director: Director = this.director): Film =
//      new Film(name, yearOfRelease, imdbRating, director)
//
//    override def toString: String = s"$name, $yearOfRelease, $imdbRating, by $director"

  }

  object Film {
    def apply(
      name: String,
      yearOfRelease: Int,
      imdbRating: Double,
      director: Director): Film =
      new Film(name, yearOfRelease, imdbRating, director)

    def highestRating(f1: Film, f2: Film): Double = {
      if (f1.imdbRating >= f2.imdbRating) f1.imdbRating else f2.imdbRating
    }
    def oldestDirectorAtTheTime(f1: Film, f2: Film): Director = {
      if (f1.directorAge >= f2.directorAge) f1.director else f2.director
    }
  }

  case object Dad{
    def rate(f: Film): Double = f match {
      case Film(_, _, _, Director("Clint", "Eastwood", 1930)) => 10.0
      case Film(_, _, _, Director("John", "McTiernan", 1951)) => 7.0
      case _ => 3.0
    }
  }

  val eastwood          = Director("Clint", "Eastwood", 1930)
  val mcTiernan         = Director("John", "McTiernan", 1951)
  val nolan             = Director("Christopher", "Nolan", 1970)
  val someBody          = Director("Just", "Some Body", 1990)

  val memento           = Film("Memento", 2000, 8.5, nolan)
  val darkKnight        = Film("Dark Knight", 2008, 9.0, nolan)
  val inception         = Film("Inception", 2010, 8.8, nolan)

  val highPlainsDrifter = Film("High Plains Drifter", 1973, 7.7, eastwood)
  val outlawJoseyWales  = Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
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


    // test director.older
    println(eastwood.yearOfBirth, mcTiernan.yearOfBirth)
    assert(Director.older(eastwood, mcTiernan) == eastwood)

    // test highestRating
    assert(Film.highestRating(darkKnight, dieHard) == 9)

    // test oldestDirectorAtTheTime
    println(s"Nolan age for Dark Knight: ${darkKnight.directorAge}")
    println(s"McTiernan age for Die Hard: ${dieHard.directorAge}")

    assert(Film.oldestDirectorAtTheTime(darkKnight, dieHard) == nolan)

    // test Dad object and rate method
    assert(Dad.rate(dieHard) == 7.0)
    assert(Dad.rate(darkKnight) == 3.0)
    assert(Dad.rate(unforgiven) == 10.0)
  }
}

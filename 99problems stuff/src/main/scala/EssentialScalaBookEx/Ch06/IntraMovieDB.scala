package EssentialScalaBookEx.Ch06

object IntraMovieDB {

  case class Film(
                   name: String,
                   yearOfRelease: Int,
                   imdbRating: Double)

  //  sealed trait FilmMaker

  case class Director(
                       firstName: String,
                       lastName: String,
                       yearOfBirth: Int,
                       films: Seq[Film]) {

    override def toString: String = {
      val firstFilm = this.films(0).name
      val filmography = films.drop(1).foldLeft(s"$firstFilm"){(state, value) => s"$state, ${value.name}"}
      s"$lastName, $firstName; DOB: $yearOfBirth, Filmography: $filmography"
    }

    def getFilms(): Seq[String] = films.map(_.name)

  }


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

  // TODO: Experiment with formatting/currying
  // sigs -  2 -> seq[dir], int  => seq[dir]
  //         1 -> seq[dir], int, int => seq[dir]
  //         1 -> seq[dir], bool => seq[dir]
  //         2 -> seq[dir] => seq[Film]
  //         1 -> seq[dir] => Int
  //         1 -> seq[dir] => Unit

  // 1 - reformat functions such that seq[Dir] is on the outside parameter list


  //  sealed trait IMDBFunc
  //    def transform
  //  case class SingleDirector extends IMDBFunc
  //  case class DirectorCollection extends IMDBFunc

  // Ross example code
  sealed trait ImdbFunc {
    type Formatted // abstract type member - we need to provide the type
  }
  trait SingleDirectorFunction extends ImdbFunc {
    type Formatted = Director => Unit
    def apply(d: Director): (String, Seq[Director])
  }
  trait DirectorCollectionFunction extends ImdbFunc {
    type Formatted = Seq[Director] => Unit
    def apply(ds: Seq[Director]): (String, Seq[Director])
  }


  def numberOfFilms(n: Int): (String, Seq[Director]) = {
    directors.filter(_.films.size > n).map(_.lastName) // TODO "collect" - filter/map
    val header = s"Filmmakers with more than $n films: "
    (header, directors.filter(_.films.size > n))
  }


  def numberOfFilms_v2(n: Int)(dirs: Seq[Director]): (String, Seq[Director]) = {
    directors.filter(_.films.size > n).map(_.lastName) //
    val header = s"Filmmakers with more than $n films: "
    (header, directors.filter(_.films.size > n))
  }

  //


  def bornBefore(year: Int): (String, Seq[Director]) = {
    val header = s"A Filmmaker born before $year:"
    (header, Seq(directors.find(_.yearOfBirth < year).getOrElse(Director("Nobody", "McNoone", 0, Nil))))
  }

  def oldTimers(year: Int, n: Int): (String, Seq[Director]) = {
    val header = s"A filmmaker born before $year  - with a back catalog of size: $n : "
    (header, directors.filter(x => x.films.size > n && x.yearOfBirth < year))
  }

  // wasn;t able to generalize it to a nice api
  // they idea
  def formatIMDBFunction(filmFunc: Int => (String, Seq[Director]), n: Int): Unit = {
    val result = filmFunc(n)
    println(result._1)
    result._2.foreach(x => println(x))
  }

  // before I wasn't separating the formatting
  // TODO come up with some intermediate values also
  // TODO maybe use StringWriter - mutable string operation
  // TODO look at .mkString -> seq._2.mkString("\n")
  // seq._1 + "\n" + seq._2.mkString("\n")
  // s”${seq.1}\n${seq._2.mkString(“\n”)}”
  // seq._2.mkString(seq._1, "\n", "\n")

  def formatIMDBResult(seq: (String, Seq[Director])): String = {
    s"${seq._1} \n ${seq._2.foldLeft(""){(state, value) => s"$value \n $state" }}" // string interpolation nested in a loop is quadratic
  }

  // experiment
  //  def formatIMDBFunction_v2(imdbFunc: ImdbFunc): imdbFunc.Formatted = imdbFunc match {
  //    case x: SingleDirectorFunction => {v : Director => println(x(v)) }
  //  }

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

    //    println(numberOfFilms(3))
    //    numberOfFilms(3)

    formatIMDBFunction (numberOfFilms, 3)

    //    numberOfFilms_v3(3)(directors)

    //    println(bornIn(1984))
    formatIMDBFunction(bornBefore, 1984)

    println(oldTimers(1960, 2))
    // how could I take an filmFunc then change it's shape to make what the formatFunc expects
    // thing that changes :
    //  whether the function is working on a Director or Seq[Dir]
    //  is the number of integers
    //      this might be currying // variadic blahblah

    println(sortDirectors())
    println((sortDirectors(false)))

    // nolan to list of his films
    println(s"Overrated films: ${nolan.films.map{x => x.name}}")

    // nolan films w/o using map
    print("Overrated films w/o map:")
    println(
      for { film <- nolan.films }
        yield film.name  // yield has to return a single value; {} after yield are not a part of the expression
    )

    println(
      for { film <- nolan.films}
        yield {
          val name = film.name // use case for statements - in functional programming are often a matter of convenience
          val count = name.size
          count
        } // yield has to return a single value
    )

    // expression  - the entire thing returns a value

    // block expression - do whatever is in the block; return last value; like a list of expressions
    //

    // statements - (in general) usually effectful; don't return a value; purpose not for value reason
    val a = 1 // statement

    // Cinephile - all films by all directors
    println(directors.flatMap(_.films.map(_.name))) // this is quadratic
    // w/o map
    val allFilms =
      for {
        director <- directors
        film <- director.films
      } yield film.name

    println(allFilms)

    // earliest mcTiernan
    println(mcTiernan.films.map(_.yearOfRelease).min)

    //High Score Table - all films sorted by extremely ridiculous metric
    println(directors.flatMap(x => (x.films)
      .map(x => (x.name, x.imdbRating)))
      .sortWith((x,y) => x._2 > y._2))

    val allRatings = {
      for {
        director <- directors
        film <- director.films
      } yield {(film.name, film.imdbRating)}
    }.sortWith((x, y) => x._2 > y._2)
    println(allRatings)

    // find the average of score of all films
    println(directors.flatMap(_.films)
      .foldLeft(0.0, 0){case (state, value) =>
        (value.imdbRating + state._1, 1 + state._2)}
    match { case (total, length) => total/length} )

    // print announcement ... Janky as heck!!
    directors.foldLeft(""){case (_, value) =>
      value.films.foreach(x => println(s"Tonight only! ${x.name.toUpperCase()} by " +
        s"${value.firstName.toUpperCase} ${value.lastName.toUpperCase}!"))
      ""
    }

    // announcement with for comp
    for {
      director <- directors
      film <- director.films

    }  println(s"Tonight only! ${film.name.toUpperCase} by ${director.firstName} ${director.lastName}!")


    println(directors.map(x => x.films.map(_.yearOfRelease).minOption))


  }

}
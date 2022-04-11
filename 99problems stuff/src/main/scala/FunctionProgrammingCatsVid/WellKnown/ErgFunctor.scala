package FunctionProgrammingCatsVid.WellKnown

//import cats.instances.map

object ErgFunctor {

  class Secret[A](val value: A) {
    private def hashed: String = ???

    override def toString: String = hashed

    def toUpper (name: Secret[String]): Secret[String] = {
//      new Secret(name.value.toUpperCase)

      map(name)(_.toUpperCase)
//      name.map(_.toUpperCase)  //why is this broken?
    }

    def toLower (name: Secret[String]): Secret[String] = {
//      new Secret(name.value.toLowerCase)

      map(name)(_.toLowerCase)
    }


    def map[A, B](secret: Secret[A])(f: A => B): Secret[B] = {
      new Secret(f(secret.value))
    }

  }

  case class Person(name: Secret[String])


}

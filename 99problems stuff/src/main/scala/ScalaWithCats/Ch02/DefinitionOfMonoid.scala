package ScalaWithCats.Ch02

object DefinitionOfMonoid {

  trait Monoid[A] {
    def combine(a: A, y: A): A
    def empty: A

    def associativeLaw[A](x: A, y: A, z: A)
                         (implicit m: Monoid[A]): Boolean = {
      m.combine(x, m.combine(y, z)) ==
        m.combine(m.combine(x, y), z)
    }

    def identityLaw[A](x: A)
                      (implicit m: Monoid[A]): Boolean = {
      (m.combine(x, m.empty) == x) &&
        (m.combine(m.empty, x) == x)
    }


  }

}

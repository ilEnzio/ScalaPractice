package ScalaWithCats.Ch04


import cats.Monad
//import cats.syntax.functor._ // for map
//import cats.syntax.flatMap._ // for flatMap
import cats.syntax.all.*

object MonadTypeClass {

  trait EMonad[F[_]] {
    def pure[F[_], A](value: A): F[A]

    def map[A, B](value: A)(f: A => B): F[A]

    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
  }


  type Id[A] = A

  def pure[A](value: A): Id[A] = value

  def map[A, B](value: A)(f: A => B): Id[B] = f(value)

  def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value) // map(value)(a => func(a))



  def main(args: Array[String]): Unit = {

  }
}

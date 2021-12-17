package EssentialScalaBookEx.Ch06

object AddingThings {
  //
  //  Write a method addOptions that accepts two parameters of type Option[Int] and
  //  adds them together. Use a for comprehension to structure your code.
  def addOptions(a: Option[Int], b: Option[Int]): Option[Int] = {
    for {
      aa <- a
      bb <- b
    } yield {
      aa + bb
    }
  }

  def addOptions_v2(a: Option[Int], b: Option[Int]): Option[Int] = {
    a.flatMap(x => b.map(y => x + y ))
  }

  def addOptions(a: Option[Int], b: Option[Int], c: Option[Int]): Option[Int] = {
    for {
      aa <- a
      bb <- b
      cc <- c
    } yield aa + bb + cc
  }

  def addOptions_v2(a: Option[Int], b: Option[Int], c: Option[Int]): Option[Int] = {
    c.flatMap(z => a.flatMap(x => b.map(y => x + y + z)))
  }


  def main(args: Array[String]): Unit = {

    val t1 = Option(4)
    val t2 = Option(3)
    val t4 = None

    println(addOptions(t1, t2))
    println(addOptions(t1, t4))
    println(addOptions(addOptions(t1, t2), t4))

    println((addOptions_v2(t1, t2)))

    println(addOptions(t1, t1, t2))
    println(addOptions_v2(t1, t1, t2))

  }

}
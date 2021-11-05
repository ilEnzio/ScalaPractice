import scala.collection.immutable.ListMap

case class School (var db: Map[Int, Seq[String]] = Map())  {
  type DB = Map[Int, Seq[String]]


  def add(name: String, g: Int) = {
//    println("nothing")

    val temp =  this.db.getOrElse(g, Seq()) :+ name
    db = db + (g -> temp)

  }

//  def db: DB = Map()

  def grade(g: Int): Seq[String] = {
    this.db.getOrElse(g, Seq())
  }

  def sorted: DB = {

    val temp = ListMap(this.db.toSeq.sortBy(_._1):_*)
    temp
  }
}


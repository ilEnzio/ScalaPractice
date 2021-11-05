import scala.collection.immutable.ListMap

case class School (var db: Map[Int, Seq[String]] = Map())  {
  type DB = Map[Int, Seq[String]]


  def add(name: String, g: Int) = {
    val temp =  this.db.getOrElse(g, Seq()) :+ name
    db = db + (g -> temp)

  }

//  def db: DB = Map()

  def grade(g: Int): Seq[String] = {
    this.db.getOrElse(g, Seq())
  }

  def sorted: DB = {
    db.keys.foreach(i => db = db + (i -> db(i).sorted))
    val finalDB = ListMap(this.db.toSeq.sortBy(_._1):_*)
    finalDB
  }
}
// another solution
//class School {
//  type DB = Map[Int, Seq[String]]
//  private var _db: DB = Map.empty
//  def add(name: String, g: Int) = _db = _db.updated(g, grade(g) :+ name)
//  def db: DB = _db
//  def grade(g: Int): Seq[String] = _db.getOrElse(g, Seq.empty)
//  def sorted: DB = Map(_db.toSeq.sortBy(_._1): _*).mapValues(_.sorted)
//}

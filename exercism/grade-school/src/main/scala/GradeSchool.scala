import scala.collection.immutable.ListMap

case class School (var db: Map[Int, Seq[String]] = Map())  {
  type DB = Map[Int, Seq[String]]


  def add(name: String, g: Int) = {
    val temp =  db.getOrElse(g, Seq()) :+ name // Refactor this for sure
    db = db + (g -> temp)                           //

  }

//  def db: DB = Map()

  def grade(g: Int): Seq[String] = {
    db.getOrElse(g, Seq())
  }

  def sorted: DB = {
    db.keys.foreach(i => db = db + (i -> db(i).sorted))
    val finalDB = ListMap(db.toSeq.sortBy(_._1):_*) // what is happening here?
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

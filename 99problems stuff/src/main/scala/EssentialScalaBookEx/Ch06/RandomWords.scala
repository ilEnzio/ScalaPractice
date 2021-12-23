package EssentialScalaBookEx.Ch06
import scala.collection.Map

object RandomWords {

//  Write a program to generate all possible sentences given the following model:
//
//    subjects are List("Noel", "The cat", "The dog");
//  verbs are List("wrote", "chased", "slept on"); and
//  objects are List("the book", "the ball", "the bed").

  val subjects = List("Noel", "The cat", "The dog")
  val verbs = List("wrote", "chased", "slept on")
  val objects = List("the book", "the ball", "the bed")

  def sentences: List[String] = for {
    s <- subjects
    v <- verbs
    o <- objects
  } yield s"\"$s $v $o.\""



  // my solutions is wonky... maybe I'm still stuck thinking OO?
  val subM = Map(
    "Noel" -> List("wrote", "chased", "slept on"),
    "The cat" -> List("meowed",  "chased", "slept on" ),
    "The dog" -> List("barked at", "chased", "slept on"))

  val objM = Map(
    "wrote" -> List("the book", "the letter", "the code"),
    "the chased" -> List("the ball", "the dog", "the cat"),
    "slept on" -> List("the bed", "the mat", "the train"),
    "meowed at" -> List("Noel", "the door", "the food cupboard"),
    "barked at" -> List("the postman", "the car", "the cat")
  )

  def sentences_v2 = {
    for {
      (s, vl) <- subM
      (v, ol) <- objM if vl.contains(v)
      o <- ol if ol.contains(o)
    } yield s"\"$s $v $o.\""

  }

  // book solution
  def verbsFor(subject: String): List[String] =
    subject match {
      case "Noel" => List("wrote", "chased", "slept on")
      case "The cat" => List("meowed at", "chased", "slept on")
      case "The dog" => List("barked at", "chased", "slept on")
    }

  def objectsFor(verb: String): List[String] =
    verb match {
      case "wrote" => List("the book", "the letter", "the code")
      case "chased" => List("the ball", "the dog", "the cat")
      case "slept on" => List("the bed", "the mat", "the train")
      case "meowed at" => List("Noel", "the door", "the food cupboard")
      case "barked at" => List("the postman", "the car", "the cat")
    }

  def allSentencesConditional: List[(String, String, String)] =
    for {
      subject <- subjects
      verb <- verbsFor(subject)
      obj <- objectsFor(verb)
    } yield (subject, verb, obj)

  def main(args: Array[String]): Unit = {



    println(sentences_v2)
  }

}

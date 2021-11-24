package EssentialScalaBookEx.Ch04


// TODO: What is the cake pattern? Why was I told to avoid it?

object CatSim_v2 {

  trait Feline {

    def colour: String
    def sound: String = "roar"

  }

  case class Cat(name: String, colour: String, food: String) extends Feline {
    override val sound: String = "meow"
  }

  case class Tiger(name: String, colour: String) extends Feline {
  }

  case class Lion(name: String, colour: String, maneSize: Int) extends Feline {
  }

  case class Panther(name: String, colour: String) extends Feline {
  }

  def main(args: Array[String]): Unit = {

  }
}

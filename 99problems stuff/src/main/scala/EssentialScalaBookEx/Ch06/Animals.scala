package EssentialScalaBookEx.Ch06

object Animals {

  //  Create a Seq containing the Strings "cat", "dog", and "penguin". Bind it to the name animals.

  //  Append the element "tyrannosaurus" to animals and prepend the element "mouse".


  def main(args: Array[String]): Unit = {
    val animals: Seq[String] = Seq("cat", "dog", "penguin")

    val moreAnimals = "mouse" +: animals :+ "tyrannosaurus"
    println(moreAnimals)

    val errorOrString = 2 +: animals

    println(errorOrString) // actually this is a string of Any

  }

}
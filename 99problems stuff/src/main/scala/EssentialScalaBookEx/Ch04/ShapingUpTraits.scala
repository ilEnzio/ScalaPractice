package EssentialScalaBookEx.Ch04

object ShapingUpTraits {

  sealed trait Shape {
//    def sides: Int
    def perimeter: Double
    def area: Double
    def color: Color
  }
// TODO how do I arrange a file?  should all traits go together?

//
//  Write a sealed trait Color to make our shapes more interesting.
//
//  give Color three properties for its RGB values;
//  create three predefined colours: Red, Yellow, and Pink;
//  provide a means for people to produce their own custom Colors with their own RGB values;
//  provide a means for people to tell whether any Color is “light” or “dark”.




  // TODO: How come we use traits rather than abstract classes?

  // TODO: I feel like this implementation is wrong - (putting name logic here)
  //  but don't know why it's better to put it in Draw.apply.  Why is it better to wait
  // the name? Maybe we want like with late binding we some how want objects to be as
  // light as possible?
  // section: 4.2.2.2 https://books.underscore.io/essential-scala/essential-scala.html

  sealed trait Color {
    def name: String = if (r + b + g >= 382) "light" else "dark"
    def r: Int
    def b: Int
    def g: Int
}

  case class CustomColor(r: Int, b: Int, g: Int) extends Color

  object Red extends Color{
    override val name = "red"
    override val r = 255
    override val b = 0
    override val g = 0
  }

  object Yellow extends Color{
    override val name = "yellow"
    override val r = 255
    override val b = 255
    override val g = 0
  }

  object Pink extends Color{
    override val name = "pink"
    override val r = 255
    override val b = 192
    override val g = 203
  }

  sealed trait Rectangular extends Shape {
    def sides: Int = 4
    def width: Double
    def height: Double
    override val perimeter: Double = (height * 2) + (width * 2)
    override val area: Double = height * width
  }

  case class Circle(radius: Int, color: Color) extends Shape{
//    override def sides: Int = 0
    override val perimeter: Double = 2 * math.Pi * radius
    override val area: Double = math.Pi * (radius * radius)
  }

  case class Rectangle(height: Double, width: Double, color: Color) extends Shape with Rectangular

  case class Square(size: Double, color: Color) extends Shape with Rectangular {
    override val width: Double = size
    override val height: Double = size
  }

  // TODO: is there a convention to refer to this object that's just performing random stuff on
  // objects in the file/package?  This Draw object feels misplaced or somehow random.
  // maybe it's just because it's named Draw.  Having objects have an apply method makes it hard
  // to understand the naming convention, if there is one.

  object Draw{
    def apply(sh: Shape): String =
      sh match {
        case x: Circle => s"A ${x.color.name} circle of radius ${x.radius}"
        case x: Rectangle => s"A ${x.color.name} rectangle of width ${x.width} and height ${x.height}"
        case x: Square => s"A ${x.color.name} square with sides length ${x.size}"
      }

    def apply(color: Color): String = color match {
      // We deal with each of the predefined Colors with special cases:
      case Red    => "red"
      case Yellow => "yellow"
      case Pink   => "pink"
      case color  => color.name
    }
  }
  def main(args: Array[String]): Unit = {

//    val testShapeSQ: Shape = Square(10)

    val testCircle: Circle = Circle(15, Red)
    val testSquare: Rectangle = Rectangle(28, 11, Pink)

    val kellColor: Color = CustomColor(220, 30, 50)
    val kellSquare: Shape = Square(1115, kellColor)

    println(Draw(kellSquare))
    println(Draw(testCircle))
    println(Draw(testSquare))

    println(Draw(kellColor))
    println(Draw(Red))
  }

}

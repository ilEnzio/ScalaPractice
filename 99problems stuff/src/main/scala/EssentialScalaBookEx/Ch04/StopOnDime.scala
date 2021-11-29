package EssentialScalaBookEx.Ch04

object StopOnDime {


  // 4.4.4.1
// various ways to implement ???
  sealed trait TrafficLight{
    def next: TrafficLight
    def next_v2: TrafficLight = this match{
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }

  case object Red extends TrafficLight {
    override def next: TrafficLight = Green
  }
  case object Green extends TrafficLight{
    override def next: TrafficLight = Yellow
  }
  case object Yellow extends TrafficLight{
    override def next: TrafficLight = Red
  }

  object LightToggle {
    def apply(current: TrafficLight): TrafficLight = current match {
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }

  def main(args: Array[String]): Unit = {

  }
}

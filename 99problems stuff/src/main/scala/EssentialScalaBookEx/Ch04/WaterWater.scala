package EssentialScalaBookEx.Ch04

object WaterWater {

  sealed trait WaterSource
  final case class Well() extends WaterSource
  final case class Spring() extends WaterSource
  final case class Tap() extends WaterSource

  class BottledWater(size: Int, source: WaterSource, carbonated: Boolean)

}

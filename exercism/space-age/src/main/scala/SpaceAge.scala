

object SpaceAge {
  def onEarth(seconds: Int): Double = {
    seconds.toFloat/31557600

  }

  def onMercury(seconds: Int): Double = {
    (seconds.toFloat/31557600) /.2408467

  }
  def onVenus(seconds: Int): Double = {
    (seconds.toFloat/31557600) /.61519726
  }

  def onMars(seconds: Double): Double = {
    (seconds/31557600) /1.8808158
  }

  def onJupiter(seconds: Int): Double = {
    (seconds.toFloat/31557600) / 11.862615
  }

  def onSaturn(seconds: Double): Double = {
    (seconds/31557600) /29.447498
  }

  def onUranus(seconds: Double): Double = {
    (seconds.toFloat/31557600) /84.016846
  }

  def onNeptune(seconds: Double): Double = {
    (seconds.toFloat/31557600) /164.79132
  }
}
package DomainModelingMadeFunctional

object ADTPractice {


  sealed trait OrderQuantity
  final case class UnitQuantity(q: Int) extends OrderQuantity
  final case class KilogramQuantity(q: Double) extends OrderQuantity

  def main(args: Array[String]): Unit = {
    val anOrdQtyInUnits = UnitQuantity(7)
    val anOrdQtyInKg = KilogramQuantity (2.4)


    def printQuantity(aOrderQty: OrderQuantity) = aOrderQty match {
      case UnitQuantity(x) => println(s"$x")
      case KilogramQuantity(x) => println(s"$x")
    }

    printQuantity(anOrdQtyInKg)
    printQuantity(anOrdQtyInUnits)
  }

}

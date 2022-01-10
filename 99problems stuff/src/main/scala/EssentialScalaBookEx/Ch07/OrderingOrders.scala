package EssentialScalaBookEx.Ch07

object OrderingOrders {


  final case class Order(units: Int, unitPrice: Double) {
    val totalPrice: Double = units * unitPrice
  }

  object OrderByTotal {   // the most common ordering could be placed as a default in the order companion object.
    implicit val ordering = Ordering.fromLessThan[Order]{
      (x, y) => x.totalPrice < y.totalPrice
    }
  }

  object OrderByQuantity {
    implicit val ordering = Ordering.fromLessThan[Order]{
      (x, y) => x.units < y.units
    }
  }

  object  OrderByUnitPrice {
    implicit val ordering = Ordering.fromLessThan[Order]{
      (x, y) => x.unitPrice < y.unitPrice
    }
  }


  def main(args: Array[String]): Unit = {

  }

}

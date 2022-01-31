package DomainModelingMadeFunctional

object ModelingComplexTypes {

  final case class Order (
       customerInfo: CustomerInfo,
       shippingAddress: ShippingAddress,
       billingAddress: BillingAddres,
       orderLines: List[OrderLine],
       amount: BillingAmount
       )

  final case class CustomerInfo()
  final case class ShippingAddress()
  final case class BillingAddres()
  final case class OrderLine()
  final case class BillingAmount()


  sealed trait ProductCode
  final case class WidgetCode(c: String) extends ProductCode
  final case class GizmoCode(c: String) extends ProductCode

  sealed trait OrderQuantity
  final case class UnitQuantity(q: Int) extends OrderQuantity
  final case class KilogramQuantity(q: Double) extends OrderQuantity

//
//  final case class ValidatedOrder(validation: UnvalidatedOrder => Either[ValidatedOrder, ValidationError])
//
//  final case class ValidationError(
//      fieldName: String,
//      errorDescription: String
//                                  )
//
//  final case class PlaceOrderEvents(
//     acknowledgementSent: AcknowledgementSent,
//     orderPlaced: OrderPlaced,
//     billableOrderPlaced: BillableOrderPlaced
//                                   )
//
//  final case class PlaceOrder(placeOrder: UnvalidatedOrder => PlaceOrderEvents)
//
//  final case class EnvelopeContents(contents: String)
//
//  sealed trait CategorizedMail
//  final case class QuoteForm() extends CategorizedMail
//  final case class  OrderForm() extends CategorizedMail
//
//  final case class CatergorizeInboundMail(f: EnvelopeContents => CategorizedMail)
//
//  final case class CalculatePrices(f: OrderForm => ProductCatalog => PricedOrder)
//


  def main(args: Array[String]): Unit = {

  }
}

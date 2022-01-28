package DomainModelingMadeFunctional

import java.util.Currency

object DomainModelComposable {


  final case class CheckNumber(checkNumber: Int)
  final case class CardNumber(cardNumber: String)

  sealed trait CardType
  final class Visa extends CardType
  final class MasterCard extends CardType


  final case class CreditCardInfo (
          cardType: CardType,
          cardNumber: CardNumber
                                  )

  sealed trait PaymentMethod
  final class Cash extends PaymentMethod
  final case class Check(number: CheckNumber) extends PaymentMethod
  final case class Card(info: CreditCardInfo) extends PaymentMethod


  final case class PaymentAmount(amount: Double)

  sealed trait Currency
  final class EUR extends Currency
  final class USD extends Currency

  final case class Payment(amount: PaymentAmount,
                           currency: Currency,
                           method: PaymentMethod)

//
//  def tryPayInvoice =
//    UnpaidInvoice => Payment => TryPaidInvoice
//
//  def convertPaymentCurrency  =
//    Payment => Currency => Payment

  def main(args: Array[String]): Unit = {

  }

}

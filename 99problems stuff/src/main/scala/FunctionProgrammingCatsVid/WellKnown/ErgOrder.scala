package FunctionProgrammingCatsVid.WellKnown

import cats._
import cats.implicits._

object ErgOrder {

  case class Account(id: Long, number: String, balance: Double, owner: String)

  object Account {
    implicit def orderById(implicit orderLong: Order[Long]): Order[Account] = Order.from((a, b) =>
    orderLong.compare(a.id, b.id))

    object Instances {

    }
  }

}

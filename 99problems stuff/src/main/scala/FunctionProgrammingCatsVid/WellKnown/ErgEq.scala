package FunctionProgrammingCatsVid.WellKnown


import cats._
import cats.implicits._

object ErgEq {

  case class Account(id: Long, number: String, balance: Double, owner: String)

  object Account {
    implicit val universalEq: Eq[Account] = Eq.fromUniversalEquals

    object Instances {
      implicit val byIdEq: Eq[Account] = Eq.instance[Account] { (v1, v2) =>
      Eq[Long].eqv(v1.id, v2.id)
        }

      implicit def byIdEq2(implicit eqLong: Eq[Long]): Eq[Account] = Eq.by(_.id)
      // compare account by number

      implicit val byNumEq: Eq[Account] = Eq.instance[Account] { (a, b) =>
        Eq[String].eqv(a.number, b.number)
      }
    }

  }


  def main(args: Array[String]): Unit = {
    val acc1 = Account(1, "12-23", 1000, "Leandro")
    val acc2 = Account(2, "12-23", 1400, "Martin")

    println(Eq[Account].eqv(acc1, acc2))

    println(Account.Instances.byIdEq.eqv(acc1, acc2))
    println(Account.Instances.byNumEq.eqv(acc1, acc2))

    import Account.Instances.byNumEq
    // or implicit val eqToUse = Account.Instances.byNumEq
    println(acc1 === acc2)



  }
}

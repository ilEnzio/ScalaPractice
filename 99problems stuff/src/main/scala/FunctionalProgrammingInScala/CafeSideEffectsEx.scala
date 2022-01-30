package FunctionalProgrammingInScala

object CafeSideEffectsEx {

  final case class Coffee(){
    def price: Double = ???
  }

  // the fact that this has a combine
  // makes me think of Semigroup
  final case class Charge(cc: CreditCard, amount: Double){
    def combine(other: Charge): Charge = {
      if (cc == other.cc) Charge(cc, amount + other.amount)
      else throw new Exception("Can't combine charges to different cards")
    }

  }

  final case class CreditCard(){
    def charge(price: Double) = ???
  }

// OO solution
  final case class Cafe () {
    def buyCoffee(cc: CreditCard): Coffee = {
      val cup = Coffee()
      cc.charge(cup.price)
      cup
    }
  // Functional solution - remove side effect;
  // return charge and coffee
    def buyCoffee2(cc: CreditCard): (Coffee, Charge) = {
      val cup = Coffee()
      (cup, Charge(cc, cup.price))
    }

    def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
      val purchases: List[(Coffee, Charge)] =
        List.fill(n)(buyCoffee2(cc))
      val (coffees, charges) = purchases.unzip
      (coffees, charges.reduce{(c1, c2) =>
        c1.combine(c2)
      })
  }

    def coalesce(charges: List[Charge]): List [Charge] =
      charges.groupBy(_.cc).values.map(_.reduce( _ combine _)).toList



  }




  def main(args: Array[String]): Unit = {

  }

}

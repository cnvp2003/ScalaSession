package scalaSession

import scala.collection.mutable.ArrayBuffer
//https://docs.scala-lang.org/overviews/scala-book/oop-pizza-example.html

sealed trait Topping
case object Cheese extends Topping
case object Pepperoni extends Topping
case object Sausage extends Topping
case object Mushrooms extends Topping
case object Onions extends Topping

sealed trait CrustSize
case object SmallCrustSize extends CrustSize
case object MediumCrustSize extends CrustSize
case object LargeCrustSize extends CrustSize

sealed trait CrustType
case object RegularCrustType extends CrustType
case object ThinCrustType extends CrustType
case object ThickCrustType extends CrustType

object FunctionalExample extends App {
  val p1 = Pizza1(MediumCrustSize, ThinCrustType, Seq(Cheese))
  val p2 = Pizza1(LargeCrustSize, ThinCrustType, Seq(Cheese, Pepperoni, Sausage))

  val address = Address1 ("123 Main Street", "Apt. 1", "Talkeetna", "Alaska", "99676")
  val customer = Customer1 ("Alvin Alexander", "907-555-1212", address)

  val o = Order1(Seq(p1, p2), customer)
  val extraPizza = Pizza1(SmallCrustSize, ThinCrustType, Seq(Cheese, Mushrooms))

  //o.addPizza(extraPizza)
  val oo = o.copy(pizzas = o.pizzas :+ extraPizza)

  // print the order
  println(s"Oders::: ${o.toString}")
  println(s"Odersoo::: ${oo.toString}")
}

case class Pizza1(crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping])
case class Order1(pizzas: Seq[Pizza1], customer: Customer1)
case class Customer1(name: String, phone: String, address: Address1)
case class Address1(street1: String, street2: String, city: String, state: String, zipCode: String)



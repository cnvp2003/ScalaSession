package scalaSession

import scala.collection.mutable.ArrayBuffer
//https://docs.scala-lang.org/overviews/scala-book/oop-pizza-example.html

object OOPExample extends App {
  val p1 = new Pizza (MediumCrustSize, ThinCrustType, ArrayBuffer(Cheese))
  val p2 = new Pizza (LargeCrustSize, ThinCrustType, ArrayBuffer(Cheese, Pepperoni, Sausage))

  val address = new Address ("123 Main Street", "Apt. 1", "Talkeetna", "Alaska", "99676")
  val customer = new Customer ("Alvin Alexander", "907-555-1212", address)

  val o = new Order(ArrayBuffer(p1, p2), customer)

  o.addPizza(new Pizza (SmallCrustSize, ThinCrustType, ArrayBuffer(Cheese, Mushrooms)))

  // print the order
  println(s"Oders::: ${o.printOrder}")
}

import scala.collection.mutable.ArrayBuffer

class Pizza (
              var crustSize: CrustSize,
              var crustType: CrustType,
              val toppings: ArrayBuffer[Topping]
            ){
//Behaviour to class
  def addTopping(t: Topping): Unit = toppings += t
  def removeTopping(t: Topping): Unit = toppings -= t
  def removeAllToppings(): Unit = toppings.clear()

  def getPrice(
                toppingsPrices: Map[Topping, Int],
                crustSizePrices: Map[CrustSize, Int],
                crustTypePrices: Map[CrustType, Int]
              ): Int = ???

  override def toString ={
    s"Size: ${crustSize}, Type: ${crustType}, Toppings: ${toppings}"
  }

}

class Order (
              val pizzas: ArrayBuffer[Pizza],
              var customer: Customer
            ){

  def addPizza(p: Pizza): Unit = pizzas += p
  def removePizza(p: Pizza): Unit = pizzas -= p

  // need to implement these
  def getBasePrice(): Int = ???
  def getTaxes(): Int = ???
  def getTotalPrice(): Int = ???

  def printOrder()={
    s"Customer: ${customer.name} >> Order: ${pizzas}"
  }

}

class Customer (
                 var name: String,
                 var phone: String,
                 var address: Address
               )
class Address (
                var street1: String,
                var street2: String,
                var city: String,
                var state: String,
                var zipCode: String
              )

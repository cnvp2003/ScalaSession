package scalaSession

//Class
class Person extends App {
  val nameVal ="Test Person1"
  //nameVal ="Test Person2"  //error as nameVal is immutable like constant

  var nameVar = "Test Person2"
  nameVar = "Test Person3"  // variable same in java

  def nameDef = "Test PersonDef"  //will be initialized on call... Lazy Initialization

  def main() ={
    println("Scala Start!")
  }
  PersonTest.testPrint
}

//Object
object PersonTest {
  new Person()
  def testPrint = s"This is test Object"
}

//Trait, Sealed Trait
trait Currency1 //interoperable with Java.

sealed trait Currency { def name: String }
case object EUR extends Currency { val name = "eur" }
case object USD extends Currency { val name = "usd" }
case class UnknownCurrency(name: String) extends Currency

//simple domain model
case class User(name: String, cart: Cart)
case class Cart(id: String, item: Item, quantity: Int)
case class Item(sku: String, price: Double, leftInStock: Int, discount: Discount)

//an ADT (Algebraic Data Type) describing types of discounts on products
trait Discount
case class NoDiscount() extends Discount
case class PercentageOff(value: Double) extends Discount
case class FixPriceOff(value: Double) extends Discount


case class PersonTest(name:String, age:Int)
/*
Case classes differ from regular classes in that they get
  pattern matching support
  default implementations of equals and hashCode
  default implementations of serialization
  a prettier default implementation of toString, and
  the small amount of functionality that they get from automatically inheriting from  scala.Product.

case Object differ from regular object:
  case object extends the Serializable trait, so they can be serialized. whereas Regular objects not.
  case object can’t have any constructor parameters
  define singleton with object
  */


/*companion object is singleton object named the same as a class.
must be defined inside the same source file as the class,
clear separation between static and non-static methods
encapsulating,
*/

//Companion object
object Person {
  new Person()
}

//Companion object
class CompanionObject(val description: String) {
  private var _status: String = "created"
  def status():String = _status
}

object CompanionObject {
  def apply(description: String): CompanionObject = new CompanionObject(description)
}


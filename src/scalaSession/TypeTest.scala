package scalaSession

import scala.util.Try

//https://www.baeldung.com/scala/generics-basics#:~:text=Most%20Scala%20generic%20classes%20are,t%20so%20obvious%20at%20first.

object PassengerTraffic extends App {
  // Some type aliases, just for getting more meaningful method signatures:
  type CoffeeBeans = String
  type GroundCoffee = String

  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  case class Water(temperature: Int)

  // dummy implementations of the individual steps:
  def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
  def heatWater(water: Water): Water = water.copy(temperature = 85)
  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "Espresso"
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "Cappuccino"

  // some exceptions for things that might go wrong in the individual steps
  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  // going through these steps sequentially:
  //completly waste of time.. so If you have multiple computations that can be computed in parallel,
  // you need to take care to create corresponding Future instances outside of the for comprehension.
/* e.g.
def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }*/

  def prepareCappuccino(): Try[Cappuccino] = for {
    ground <- Try(grind("arabica beans"))  //remove try, should be then "ground <- grind("arabica beans")"
    water <- Try(heatWater(Water(25)))
    espresso <- Try(brew(ground, water))
    foam <- Try(frothMilk("milk"))
  } yield combine(espresso, foam)

  println(s"Your ${prepareCappuccino().get} is Ready!!")

}
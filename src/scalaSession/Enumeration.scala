package scalaSession

object Enumeration extends App {
/*
  object Weekday extends Enumeration {
    val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
  }

  Weekday.Monday.toString //res0: String = Monday
  Weekday.withName("Monday") //res1: Weekday.Value = Monday
  Weekday.values.toList.sorted //res0: List[Weekday.Value] = List(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)

  //other way of declaring with default value changed
  object Weekday1 extends Enumeration {
    val Monday = Value(1)
    val Tuesday = Value(2)
    val Wednesday = Value(3)
    val Thursday = Value(4)
    val Friday = Value(5)
    val Saturday = Value(6)
    val Sunday = Value(0)
  }

  Weekday.values.toList.sorted //res1: List[Weekday.Value] = List(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday)

  // 2 Drawback are there
  // another Enumeration in same file create problems while calling it
  object OtherWeek extends Enumeration {
    val A, B, C = Value
  }*/
/*  def enumTest(enum: Weekday.Value) = {    println(s"value: $enum")  }
  def enumTest(enum: OtherWeek.Value) = {    println(s"value: $enum")  }*/

  //There’s no exhaustive matching check during compile, where as Sealed trait gives warning
  sealed trait Weekday
  case object Monday extends Weekday
  case object Tuesday extends Weekday
  case object Wednesday extends Weekday
  case object Thursday extends Weekday
  case object Friday extends Weekday
  case object Saturday extends Weekday
  case object Sunday extends Weekday

  def test(weekday: Weekday) = {
    weekday match {
      case Monday => println("I hate Mondays")
      case Sunday => println("The weekend is already over? :( ")
      case _ => println("default ")
    }
  }

  println(s"test ${test(Wednesday)}")

  /*
  * warning: match may not be exhaustive.
  * It would fail on the following inputs: Friday, Saturday, Thursday, Tuesday, Wednesday
  *             weekday match {
  * test: (weekday: Weekday)Unit
  * */

}

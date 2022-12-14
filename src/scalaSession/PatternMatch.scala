package exercise

object PatternMatch {

    val nums: List[Int] = List(1, 2, 3, 4)
    val fruit: List[String] = List("apples", "oranges", "pears")

    val mix: List[Any] = List(1, 2, "apples", "oranges", "pears")

    def main(args: Array[String]) {
     // println(matchTest(nums))
     // println(matchTest(fruit))
      println(matchTest(mix))
    }

    def matchTest(x: List[Any]):Any = x.map { y =>
      y match {
        case 1 => "one"
        case y: Int => "scala.Int"
        case 2 => "Twooo"
        case "apples" => 2
        case _ => "many"
      }
    }

  //case object
  sealed trait Message
  case object SuccessMessage extends Message{
    val ss = "test"
  }
  case object FailureMessage extends Message


  def log(str: String, msgType: Message) = msgType match {
    case SuccessMessage => println("Success: " + str+ SuccessMessage.ss)
    // case FailureMessage => println("Failure: " + str) //Warn: match may not be exhaustive.
  }

  case class Person(id:String="test")

  val person1 = Person

  //case class
  sealed trait Message1 { def msg: String }
  case class Success(msg:String) extends Message1
  case class Failure(msg:String) extends Message1

  def log1(msg: Message1) = msg match {
    case Success(str) => println("Success: " + str)
    //case Failure(str) => println("Failure: " + str) //Warn: match may not be exhaustive.
  }

 log("test", SuccessMessage)
  log1(Success("te"))

  }



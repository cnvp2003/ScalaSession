package scalaSession

import scala.util.{Failure, Success, Try}

//https://docs.scala-lang.org/overviews/scala-book/functional-error-handling.html

object ErrorHandling extends App {
  //try catch block
    lazy val zero = 0
    try {
      val result = zero / 40
    } catch {
      case e: ArithmeticException => println("arithmetic exception")
      case _                      => println("something went wrong")
    } finally {
      println("final..")
    }

  //Either
  case class FailureResponse(msg: String)
  //By convention failure values are left in Either
  def division(a: Int, b: Int): Either[FailureResponse, Int] = {
  //Try(a / b).toEither
    try {
      Right(a / b)
    } catch {
      case _ => Left(FailureResponse("Not divide by zero"))
    }
  }

  division(44, 0).fold(
    error => println("failed"),
    success => println (s"$success")
  )

  //Option, Some, None
  def divide(x: Int, y: Int): Option[Int] =
    try {
      Option(x / y)
    } catch {
      case _ => None
  }

  val result: Option[Int] = divide(44, 0)
  println(result)


  //Try
  def toInt(s: String): Try[Int] = Try {
    Integer.parseInt(s.trim)
  }

  toInt("10") match {
    case Success(i) => println(i)
    case Failure(s) => println(s"Failed. Reason: $s")
  }

}




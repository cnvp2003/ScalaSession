package akkaSession

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.{ask, pipe}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import akka.util.Timeout

object AskTell extends App {

  val system = ActorSystem("AskTell-Pattern")
  val myActor = system.actorOf(Props[TestActor1], "Test1")

  // (1) this is one way to "ask" another actor
  implicit val timeout = Timeout(5 seconds)
  val future = myActor ? "hello"
  //val future = myActor ? "hellooo"
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(s"Result1: $result")

  import scala.concurrent.ExecutionContext.Implicits.global  //required for for-yield and map function
  for {
    res <- future
  } yield (println(s"Result1 with For:  $res"))


  // (2) this is a slightly different way to ask another actor
  val future2: Future[String] = ask(myActor, "hello").mapTo[String]
  val result2 = Await.result(future2, 1 second)
  println(s"Result2: $result2")

  // (3)
  myActor ? "hello" map{x => println(s"Map Result: ${x}")}

  system.shutdown

  //shutdown actorsystem
  system.terminate()
}


class TestActor1 extends Actor {
  override def preStart(): Unit = {
    println(s"Prestarting...")
  }
  def receive = {
    case "hello" =>
      sender ! "Hello Back"
      //println("hello back at you")
    //case "hellooo"       => someFutureCall("hellooo").pipeTo(sender)
    case _       => println("huh?")
  }

  import scala.concurrent.ExecutionContext.Implicits.global
  def someFutureCall(name: String): Future[Int] = Future {
    // assume a long running database operation to search
    10
  }
}


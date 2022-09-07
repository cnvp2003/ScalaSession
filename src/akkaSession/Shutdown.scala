package akkaSession

import java.util.Date
import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

class Test1 extends Actor {

  def receive = {
    case msg =>
      println(s"[${new Date().toString}]I received a message: $msg")
  }
}

object Test1 {
  def props = Props[Test1]
}

class Test2 extends Actor {
  def receive = {
    case msg =>
      println(s"[${new Date().toString}]I received a message: $msg")
  }
}

object Test2 {
  def props = Props[Test2]
}

object Shutdown extends App{
  val system = ActorSystem("shutdown")

  val test1 = system.actorOf(Test1.props, "Test1")
  val test2 = system.actorOf(Test2.props, "Test2")

  test2 ! "Hello World"
  test2 ! PoisonPill

  //shutdown actorsystem
  //system.terminate()
}
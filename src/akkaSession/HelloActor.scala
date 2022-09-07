package akkaSession

import akka.actor.{Actor, ActorSystem, Props}

class HelloActor extends Actor {
  def receive = {
    case "hello" => {
      //bussness
      println("Return, back at you")
    }
    case _       => println("Heyy?")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "hello-actor")
  helloActor ! "hello"
  helloActor ! "random msg"

  //shutdown actorsystem
  system.terminate()
}

class testActor extends Actor{
  def receive ={
    case ""  => println(s"actor")
    case _=>
  }
}

object Mateast extends App{
  val system = ActorSystem("test")
  val testA = system.actorOf(Props[testActor], "test")
  testA ! ""
  testA ! "hello"
}
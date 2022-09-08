import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.{ask, pipe}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import akka.util.Timeout

class HelloActor extends Actor {
  def receive = {
    case "hello" => {
      //bussness
      println("Return, back at you")
    }
    case _       => println("Heyy?")
  }
}

object Dispatcher extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "hello-actor")
/*  helloActor ! "hello"
  helloActor ! "random msg"*/

  val defaultDispatcherConfig = system.settings.config.getConfig("akka.actor.default-dispatcher")
  //println(s"default-dispatcher = $defaultDispatcherConfig")

  val dispatcherType = defaultDispatcherConfig.getString("type")
  println(s"Type: $dispatcherType")
  val dispatcherThroughput = defaultDispatcherConfig.getString("throughput")
  println(s"Through Put: $dispatcherThroughput")
  val dispatcherParallelismMin = defaultDispatcherConfig.getInt("fork-join-executor.parallelism-min")
  println(s"Minimum parallelism: $dispatcherParallelismMin")
  val dispatcherParallelismMax = defaultDispatcherConfig.getInt("fork-join-executor.parallelism-max")
  println(s"Maximum parallelism: $dispatcherParallelismMax")

  //shutdown actorsystem
  system.terminate()


}


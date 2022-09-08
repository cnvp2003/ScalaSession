package akkaSession

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.{DefaultResizer, RoundRobinPool}
import akkaSession.Worker.Work

import scala.util.Random

class Worker extends Actor {
  def receive = {
    case msg:Work =>
      println(s"I received work message and my Actor Ref : ${self.toString()}")
  }
}

object Worker {
  case class Work()
  def props = Props[Worker]
}

//system.actorOf   -> actor for actor system
//context.actorOf   -> actor for another actor like parent-child
class Router extends Actor {
  var routees :List[ActorRef] = _
  override def preStart(): Unit = {
    routees = List.fill(5)(              //Pool of 5 actors
      context.actorOf(Worker.props) //Random unique name for worker actor
    )
  }

  def receive = {
    case msg:Work =>
      println(s"I m router and received message paa ${self.path}")
      routees(Random.nextInt(routees.size)) forward msg   //Routing strategies can be applied like Round-Robin
  }
}


object Routing extends App {
  val system = ActorSystem("Router")
  val router = system.actorOf(Props[Router], name = "Router-actor")

  router ! Work()
  router ! Work()
  router ! Work()

  Thread.sleep(100)
  system.terminate()
}

// We are using a resizable RoundRobinPool.
/*val resizer = DefaultResizer(lowerBound = 5, upperBound = 10)
val props = RoundRobinPool(5, Some(resizer), supervisorStrategy = supervisorStrategy)
.props(Props[DonutStockWorkerActor])
val donutStockWorkerRouterPool: ActorRef = context.actorOf(props, "DonutStockWorkerRouter")*/


/******************************************************************************************/
// Router Groups
class RouterGroup(routess:List[String]) extends Actor {
  def receive = {
    case msg:Work =>
      println(s"I m router group and received message ${self.path}")
      //Routing strategies can be applied like Round-Robin
      context.actorSelection(routess(Random.nextInt(routess.size))) forward msg
/*
      //using RoundRobinPool
      val resizer = DefaultResizer(lowerBound = 2, upperBound = 5)
      val props = RoundRobinPool(5, Some(resizer), supervisorStrategy = supervisorStrategy)
        .props(Props[Worker])
      context.actorOf(props, "RoundRobinWorker")  forward msg*/
  }
}
/*object RouterGroup{
  def apply(routess: List[String]): RouterGroup = new RouterGroup(routess)
  def prop = Props[RouterGroup]
}*/

/*
//Group actors and define unique names for actors
object RoutingGroupAPP extends App {
  val system = ActorSystem("Router-Group")
  system.actorOf(Props[Worker], "W1")
  system.actorOf(Props[Worker], "W2")
  system.actorOf(Props[Worker], "W3")
  system.actorOf(Props[Worker], "W4")

  val workers:List[String] = List(
    "/user/W1",
    "/user/W2",
    "/user/W3",
    "/user/W4")


  val routerGroup = system.actorOf(Props(classOf[RouterGroup], workers))
  //val routerGroup = system.actorOf(RouterGroup.apply(workers).props())
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  Thread.sleep(100)
  system.terminate()
}
*/
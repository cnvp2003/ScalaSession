package akkaSession

import akka.actor.{Actor, ActorIdentity, ActorRef, ActorSystem, Identify, PoisonPill, Props}

/** Difference of Actor ref, path selection, actor selection
Actor reference will not be same on creation and deletion
but actor selection will be same as it is based on name **/

object RefSelectionPath extends App {
  val system = ActorSystem("Actor-Path")
/*

  val counter1 = system.actorOf(Counter.props, "Counter") //actor ref
  println(s"Actor reference for counter1: ${counter1}")

  val actorSelection1 = system.actorSelection("coiunter") //actor selection based on path like /user/coiunter
  println(s"Actor selection for counter1: ${actorSelection1}")

  counter1 ! PoisonPill

  Thread.sleep(100)

  val counter2 = system.actorOf(Counter.props, "Counter") //In same actor-system can not have identical names
  println(s"Actor reference for counter2: ${counter2}") //differnt ref created

  val actorSelection2 = system.actorSelection("coiunter") //stays same
  println(s"Actor selection for counter2: ${actorSelection2}")
*/


  //println(s"Actor selection Identify(None): ${actorSelection2 ! Identify(None)}")

  // To get the reference of current live actor
  val system1 = ActorSystem("Watch-Actor-Selection")
  val counter = system1.actorOf(Counter.props, "Counter") //In different actor-system can have same name /user/Counter
  println(s"Actor reference for counter1:::: ${counter}")
  val counterActorSelection = system.actorSelection("Counter")
  println(s"Actor reference for counterActorSelection: ${counterActorSelection}")
  val watcher = system1.actorOf(Watcher.props, "Watcher")
  //println(s"Actor selection for counter1: ${watcher}")

Thread.sleep(1000)
  system.terminate()
  system1.terminate()
}

class Counter extends Actor {
  import Counter._
  var count =0

  def receive = {
    case Inc(x) => count += x
    case Dec(x) => count -= x
  }
}

object Counter {
  final case class Inc(num:Int)
  final case class Dec(num:Int)

  def props = Props[Counter]
}

class Watcher extends Actor {
  var counterRef:ActorRef = _
  val selectionPath = context.actorSelection("/user/Counter")

  selectionPath ! Identify(None)

  override def receive = {
    case ActorIdentity(_, Some(ref)) => println(s"Actor reference for counter is Watcher >>>> ${ref}")
    case ActorIdentity(_, None) => println(s"Actor selection for actor does not exist")
  }
}
object Watcher {
  def props = Props[Watcher]
}

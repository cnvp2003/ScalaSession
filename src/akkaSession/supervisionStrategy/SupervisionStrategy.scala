package akkaSession.supervisionStrategy

import scala.language.postfixOps
import scala.concurrent.duration._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import akka.actor.{ ActorRef, ActorSystem, Props, Actor }

class WorkerActor extends Actor {
  import WorkerActor._

  override def preStart() = {
    println("preStart....")
  }

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    println("preRestart...")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable) = {
    println("postRestart...")
    super.postRestart(reason)
  }

  override def postStop() = {
    println("postStop...")
  }

  def receive = {
    case "Resume" =>
      throw ResumeException
    case "Stop" =>
      throw StopException
    case "Restart" =>
      throw RestartException
    case _ =>
      throw new Exception
  }
}

object WorkerActor {
  case object ResumeException extends Exception
  case object StopException extends Exception
  case object RestartException extends Exception
}

class SupervisorActor extends Actor {
  import WorkerActor._

  var childRef: ActorRef = _

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 5, withinTimeRange = 1 second) {
      case ResumeException      => Resume
      case RestartException     => Restart
      case StopException        => Stop
      case _: Exception            => Escalate
    }


  override def preStart() = {
    // Create Aphrodite Actor
    childRef = context.actorOf(Props[WorkerActor], "WorkerActor")
    Thread.sleep(100)
  }

  def receive = {
    case msg =>
      println(s"supervisorActor received ${msg}")
      childRef ! msg
      Thread.sleep(100)
  }
}

object SupervisionStrategy extends App {

  // Create the 'supervision' actor system
  val system = ActorSystem("supervision")

  // Create supervisorActor Actor
  val supervisorActor = system.actorOf(Props[SupervisorActor], "supervisorActor")

  //   supervisorActor ! "Resume"
  //   Thread.sleep(1000)
  //   println()

  //  supervisorActor ! "Restart"
  //  Thread.sleep(1000)
  //  println()

  supervisorActor ! "Stop"
  Thread.sleep(1000)
  println()

  system.terminate()
}
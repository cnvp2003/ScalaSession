package scalaSession

import scala.concurrent.{Await, Future, future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FutureTest extends App{
    //synchronous blocking function
    val blockingFunction: Int = {
      Thread.sleep(10000)
      34
    }
    println(s"Blocking call done.")


    def getUser(): Future[String] = Future {
      "Test User"
    }

    //Asyn blocking function
    //requires thread pool which wil be provided with execution context
    val future1 = Future {
      Thread.sleep(20000) //20 sec // blocking call
      42
    }

    val someFutureFunction: Future[Int] = Future {
      Thread.sleep(10000)
      34
    }

    //Like a list we can map, filter, flatMap, and iterate over a Future to transform it’s result into another future.
    someFutureFunction.map { result =>
      //Database.save(result)
      println(s"save to Database")
    }

  someFutureFunction.onComplete {
    case Success(response) => println(s"Success")
    case Failure(response) => println(s"Failed.. $response")
  }

  someFutureFunction.onSuccess{
    case x=> println(s"Success")
  }

  someFutureFunction.onFailure{
    case x=> println(s"failed $x")
  }

    //Chain futures on complete.
    val productsFuture =
      getUser
        .map { user =>
          println(s"save to Database")
          //Database.save(user)
        }.map { dbResponse =>
        println(s"get Products")
        //Products.get(dbResponse.user.id)
      }

    def add5(i: Int): Future[Option[Int]] = {
      Future {
        if (i > 5) {
          println("Received request " + i)
          Thread.sleep(1000)
          println("returning result for request " + i)
          Some(i + 5)
        } else {
          None
        }
      }
    }

    //Sequential execution with future-chaining
    val futureSeq: Future[Iterable[Int]] = add5(40).flatMap { yOpt =>
      Future.sequence(yOpt.map { y =>
        add5(y).map { xOpt =>
          xOpt.map { x =>
            println(s"INSIDE:>>> $x")
            x
          }.getOrElse(0)
        }
      })
    }
    Thread.sleep(4000)

    futureSeq.map { xa =>
      println(s"FINAL: ${xa}")
    }

    Thread.sleep(4000) // this for to wait to get execution result

    //Sequential execution with blocking
    def addResult = Future {
      Seq(1, 2, 3, 4).map { i =>
        println("calling request to process " + i)
        Await.result(add5(i), Duration.Inf) // here call should go sequentially like when 1 is complete then 2nd
      }
    }

}


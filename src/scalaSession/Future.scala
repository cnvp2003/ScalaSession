/*
package scalaSession

import scala.concurrent.Future

object Future extends App {

  //def getUser():Future[User] = ???

  val future = Future {
    val result = someLongRunningThing()
    result
  }

  //Like a list we can map, filter, flatMap, and iterate over a Future to transform it’s result into another future.
  future.map { result =>
    Database.save(result)
  }

  //Chain futures on complete.
  val productsFuture =  Future {
    getUser()
  }.map { user =>
    Database.save(user)
  }.map { dbResponse =>
    Products.get(dbResponse.user.id)
  }
}


  //Sequential execution with future-chaining
    val aa: Future[Iterable[Int]] = add5(40).flatMap { yOpt =>
    Future.sequence(yOpt.map {y =>
       add5(y).map { xOpt =>
          xOpt.map { x =>
            println(s"INSIDE:>>> $x")
            x
          }.getOrElse(0)
        }
    })
    }
    Thread.sleep(4000)

    aa.map{ xa =>
      println(s"FINAL: ${xa}")
    }

    Thread.sleep(4000)  // this for to wait to get execution result
  }

  //Sequential execution with blocking
  def a = Future {
    Seq(1,2,3,4).map { i =>
      println("calling request to process " + i)
      Await.result(add5(i),Duration.Inf) // here call should go sequentially like when 1 is complete then 2nd
    }
  }

  def add5(i:Int): Future[Option[Int]] = {
    Future {
      if(i > 5){
        println("Received request "+ i)
        Thread.sleep(1000)
        println("returning result for request "+ i)
        Some(i + 5)
      }else{
        None
      }
    }
  }

*/

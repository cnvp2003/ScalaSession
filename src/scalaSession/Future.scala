/*
package assignment

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




*/

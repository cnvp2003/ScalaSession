package scalaSession

import java.util.UUID
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
object ForComprehension extends App {
  //blocking function
  val blockingFunction:Int = {
    Thread.sleep(10000)
    34
  }

  println(s"Blocking call done.")

  //Asyn blocking function
  //requires thread pool which wil be provided with execution context
  val future1 = Future {
    Thread.sleep(20000) //20 sec //blocking call
    42
  }
  //Asyn non-blocking function
  val future2 = Future {
    Thread.sleep(10000) //10 sec
    44
  }

  //Run parallel and get result once
  //will wait for both futures to complete
  //Asyn non-blocking function
  val result= for {
    result0 <- future1
    result1 <- future2
  } yield {
    println(s"res0::: ${result0}")
    println(s"res1::: ${result1}")
    (result0, result1)
  }

  println(s"res::: ${result} ")


  //another example
  case class User(Id:Long, name:String, profile: Profile)
  case class Profile(Id:Long, name:String, dept: String)

  def findUserById(id: Long): Future[User] = ???
  def findProfileByUser(user: User): Future[Profile] = ???

  //Seqeuntial //Future chaining
  def findProfileByUserId(id: Long): Future[Profile] =
    for {
      user    <- findUserById(id)
      profile <- findProfileByUser(user)
    } yield profile


  val resultPrfl: Future[Profile] = findUserById(123).flatMap { user =>
    findProfileByUser(user)
  }

  //In general example
  //No return type for for loop
  for (i <- 1 to 5) {
   Thread.sleep(10000) //10 sec... check here earlier future result
    println(s"x $i = ${2 * i}")
  }

  // For yield loop (Transform collection from one type to another)
  val a = List(2, 5, 1, 4, 8)
  val xx = for {
    e <- a
    s = s"${e*2}-Value"
  } yield {
    println(s" yielddd $s")
    s
  }
}

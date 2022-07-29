package scalaSession

import java.util.UUID
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
object ForComprehension extends App {

  val future1 = Future {
    Thread.sleep(20000) //20 sec
    42
  }
  val future2 = Future {
    Thread.sleep(10000) //10 sec
    44
  }

  //Run parallel and get result once
  //will wait for both futures to complete
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

  //Seqeuntial
  def findProfileByUserId(id: Long): Future[Profile] =
    for {
      user    <- findUserById(id)
      profile <- findProfileByUser(user)
    } yield profile

}

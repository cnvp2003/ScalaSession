/*
package akkaSession

import java.util.concurrent.TimeUnit

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.impl.Throttle
import akka.stream.{ActorMaterializer, ThrottleMode}
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

object testAkkStr extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  /* def testAStr1 = {
     val dd = Source(1 to 100)
       .grouped(7)
       .throttle(5, FiniteDuration(15, TimeUnit.SECONDS), 3, ThrottleMode.Shaping)
       .map { updateReq =>
         // multiFsmUpdate(Json.obj("fsms" -> Json.toJson(updateReq)))
         println(s"heey ${updateReq}")
         updateReq
       }
     println(s"out")
     dd
   }.runWith(Sink.ignore)

   testAStr1

   def testAStr = {
     /*Source(0 to 100).throttle(5, FiniteDuration(1, TimeUnit.SECONDS), 2, ThrottleMode.Shaping)
       .map { updateReq =>
         // multiFsmUpdate(Json.obj("fsms" -> Json.toJson(updateReq)))
         println(s"heey ${updateReq}")
       }*/
     val numbers = 1 to 10
     //Source(0 to 10000).throttle(10, FiniteDuration(1, TimeUnit.SECONDS),  100, ThrottleMode.shaping)
     Source
       .fromIterator(() => numbers.iterator)
       .throttle(5, FiniteDuration(1, TimeUnit.SECONDS), 2, ThrottleMode.Shaping)
       .map(_ => {
         println(s"fsdsf")
       })

     /* Source.fromIterator(() => Iterator.continually(1))
       .throttle(5, FiniteDuration(1, TimeUnit.SECONDS), 2, ThrottleMode.Shaping)
       .take(2)
       .runForeach(x => x)*/

   }*/

  //testAStr

  def test = {
    implicit val system = ActorSystem("Sys")
    implicit val materializer = ActorMaterializer()

    val numbers = 1 to 100

    //We create a Source that will iterate over the number sequence
    val numberSource = Source(0 to 100).throttle(50, FiniteDuration(1, TimeUnit.SECONDS), -1, ThrottleMode.Shaping)

    //Only let pass even numbers through the Flow
    val isEvenFlow = Flow[Int].filter((num) => num % 2 == 0)
    //val isEvenFlow = Flow[Int]

    //Create a Source of even random numbers by combining the random number Source with the even number filter Flow
    val evenNumbersSource = numberSource.via(isEvenFlow)

    //A Sink that will write its input onto the console
    val consoleSink = Sink.foreach[Int](println)

    //Connect the Source with the Sink and run it using the materializer
    evenNumbersSource.runWith(consoleSink)

  }

  // test

}


*/

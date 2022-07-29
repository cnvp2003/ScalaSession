package scalaSession

object FunctionalCombinators extends App {

  val m = Map( "a" -> 2, "b" -> 3 )
  m.map{ x=>
    x._1 + x._2
  }  // val res1: scala.collection.immutable.Iterable[String] = List(a2, b3)

  // difference with transform is you can influence the result of the new values by the value of their keys.
  m.transform((key, value) => key + value) //Map[String, String](a -> a2, b -> b3)

  //flatMap
  //fold, leftFold, rightFold
  //reduce, leftReduce, rightReduce



}

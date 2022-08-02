package scalaSession.functionalComibanators

object MapFunction {
  def main(args: Array[String]) {
    val list = (1 to 10).toList

    val testMap = Map(1 -> "test1", 2->"test2")
    //val testMap1 = testMap + Map(3 -> "test3")

    // imutable map
    val map1 = list.foldLeft(Map.empty[Int, String])(
      (map, value) => map + (value -> value.toString)
    )

    // this is equivalent to
    val map11 = list.foldLeft(Map.empty[Int, String])(
      (map, value) => map + ((value, value.toString))
    )

    // imutable map with more complex value (note the extra parenthesis)
    val map2 = list.foldLeft(Map.empty[Int, String])(
      (map, value) => map + (value -> ("number:" + value.toString))
    )

    // mutable Map
    val map3 = list.foldLeft(scala.collection.mutable.Map.empty[Int, String])(
      (map, value) => map += (value -> value.toString)
    )

    // Basic Map operations
    val map4 = Map[Int, Int]() // immutable map
    val map41 = map4 + (1 -> 1) // creates a new Map with (1,"1") as key-value pair
    val map42 = map4 + ((2, 2), (3, 3))
    val map43 = map4 + (2 -> 2, 3 -> 3)

    val map5 = scala.collection.mutable.Map[Int, Int]() // mutable Map
    map5 += 2 -> 2
    map5 += ((2, 2)) // same as above
    map5 += ((1, 1), (2, 2))
    map5 += (1 -> 1, 2 -> 2)

    map5 + ((8, 8)) // creates a NEW MAP with KV (8,8), EVEN THOUGH map5 is mutable
    //   map5 =- 2 // removes key 2
    map5 - 2 // creates a NEW MAP without key 2

    println(s"map4 ${map4}")
    println(s"map41 ${map41}")
    println(s"Mutable map5 ${map5}")

  }
}

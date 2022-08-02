package scalaSession.functionalComibanators

object FoldLeft {

  //def foldLeft[B](z: B)(op: (B, A) ⇒ B): B     //signature of foldLeft

  def main(args: Array[String]) {

    val numbers = List(5, 4, 8, 6, 2)
    //val numbers = Nil //list.empty
    val resultFold = numbers.fold(0) { (a, i) =>
      a + i
    }
    println(s"Fold => ${resultFold}")  // result = 25

    val prices = List.fill(10)(util.Random.nextInt(100))
    println(s"LEFT => ${prices.foldLeft(5.0)(_ + _)}")

    val sum = prices.foldLeft(0.0)(_ + _)
    println(s"Sum = $sum")

    val ss = prices.foldLeft(0) {
      (acc, l) =>
        println(s"SS $acc ... $l")
        acc + l
    }

    println(s"Right => ${prices.foldRight(5.0)(_ + _)}")

    val sumR = prices.foldRight(0.0)(_ + _)
    println(s"Sum = $sumR")
    println(s"Sum ss = $ss")

    //another example
    //find youngest person from list
    case class Person(name: String, age: Int)
    val people: Map[Int, Person] = {
      Map(1 -> Person("Amol", 30), 2 -> Person("John", 25), 3 -> Person("Devansh", 15), 4 -> Person("David", 22))
    }

    val peopleList: List[Person] = people.foldLeft(List.empty[Person])((people, current) =>
      people :+ current._2)
     // assert(peopleList == List(Person("Amol", 30), 2 -> Person("John", 25), 3 -> Person("Devansh", 15), 4 -> Person("David", 22)))

    //scala 2.13 changes
 /*   val youngestPerson: Person = people.reduceLeft((youngestPerson, currentPerson) => {
      if (youngestPerson.age > currentPerson.age) {
        currentPerson
        else {
          youngestPerson
        })
      }*/


    val numbersReduce: Seq[Double] = Seq(1.5, 2.0, 2.5)
    println(s"Reduce:: ${numbersReduce.reduce(_ + _)}")
  }
}

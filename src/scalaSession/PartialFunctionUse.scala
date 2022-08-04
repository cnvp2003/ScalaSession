package scalaSession

object PartialFunctionUse {
  def main(args: Array[String]) {
    println(List(3, 2, 1, 0).map {
      case n: Int if (n != 0) => 42.0 / n
      case 0 => 0
    })
    //List(3, 2, 1, 0).map { case n: Int if (n != 0) => 42.0 / _ }
    println(s"First Result: ${List(3, 2, 1, 0).collect { case n: Int if (n != 0) => 42.0 / n }}") //scala provided

    println("new: "+List(3, 2, 1, 0).map {
      case n: Int if (n != 0) => 42.0 / n
      case z: Int=> 0
    }.filter(_ >0))

    // custom function
    def squareRoot: PartialFunction[Double, Double] = {
      case x if x >= 0 => Math.sqrt(x)
    }

    squareRoot.isDefinedAt(2)  //true
    squareRoot.isDefinedAt(-2) //false


    //Chaining of partial function
    val positive: PartialFunction[Int, Int] = { case x if x >= 0 => x }

    val isOdd: PartialFunction[Int, Boolean] = { case x if x % 2 == 1 => true }
    val isEven: PartialFunction[Int, Boolean] = { case x if x % 2 == 0 => true }

    val evenCheck: PartialFunction[Int, Boolean] = positive andThen isEven    //Chaining
    val oddCheck: PartialFunction[Int, Boolean] = positive andThen isOdd      //Chaining

    evenCheck.isDefinedAt(-2) //false
    evenCheck.isDefinedAt(2)  //true

    val sample = List(1, 45, 10, 25)

    val odd: PartialFunction[Int, Int] = { case x if x % 2 == 1 => x }
    val even: PartialFunction[Int, Int] = { case x if x % 2 == 0 => x }
    val evenOddCheck: PartialFunction[Int, Int] = even orElse odd         //Chaining

    println(s"Test Partial Function: ${sample collect evenOddCheck}")
    println(s"Test Partial Function Even: ${sample collect even}")
    println(s"Test Partial Function Odd: ${sample collect odd}")


  //The collect method takes a PartialFunction as argument
  // and maps the values defined for this partial function over it,
  // skipping those outside the definition domain.
    val greaterThan20: PartialFunction[Any, Int] = { case i: Int if i > 20 => i }
    List(1, 45, 10, "blah", true, 25) collect greaterThan20   //List(45, 25)
    List(1, 45, 10, "blah", true, 25) collect greaterThan20   //List(45, 25)

    println("SomeText::"+List(1, 45, 10, "blah", true, 25).map(greaterThan20))

  }

}
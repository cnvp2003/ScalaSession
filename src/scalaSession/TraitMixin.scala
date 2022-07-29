package scalaSession

class Printer {
  def test(str: String): Unit = {
    println(s"class "+str)
  }
}

// trait extends class is bad practice... should be avoided
trait Pretty extends Printer {
  override def test(str: String): Unit = {
    println("In Pretty " + str)
    super.test("prettyoooo " + str)
    println("pretty " + str)
  }
}

trait NewLine extends Printer {
  override def test(str: String): Unit = {
    println("In NewLine "+str.capitalize)
    super.test(str + " *new line!*")
    println("new line "+ str + " *new line!*")
  }
}

trait Caps extends Printer {
  override def test(str: String): Unit = {
    println("In caPss "+str.capitalize)
    super.test(str.capitalize)
    println("caPss "+str.capitalize)
  }
}

/*Keep in mind that, like the Call Super anti-pattern, the Stackable Traits pattern doesn't really work for all methods in general (it is called dynamic binding)-- it only works for methods that are easily composed in a linear fashion*/

object TraitMixin {
  def main(args: Array[String]) {
    // (new A).op
    // res0: String = bar

    //(new B).op
    // res1: String = foo

    val p1 = new Printer with Pretty with NewLine with Caps
    //val p1 = new Printer with Pretty
    p1.test("hello")
  }
}
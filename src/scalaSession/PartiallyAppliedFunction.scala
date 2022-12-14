package scalaSession

object PartiallyAppliedFunction extends App {

  def plus(a: Int)(b: Int) = a + b

  def plus2 = plus(2)(_) //Partially applied function
  def plus3 = plus(3)(_) //Partially applied function
  def plus4 = plus(4)(_) //Partially applied function

  plus2(5) //result 7
  plus(10)(43)

  //implicit
  case class Color(value: String)
  case class DrawingDevice(value: String)

  def write(text: String)(implicit color: Color, by: DrawingDevice) =
    s"""Paint "$text" in ${color.value} color by ${by.value}."""


  implicit val red: Color = Color("red")
  implicit val drawingDevice: DrawingDevice = DrawingDevice("pen")

  println(s"Implicit Result: ${write("Hello")}")

/*  implicit val xyz: String = "testSession"
  implicit val x: Int = 123

  def testPerson(id:Int, name:String)(implicit i:Int, dc:String, ii:Int)={
    db.insert(id, name)
  }

  testPerson(2, "test")*/

}

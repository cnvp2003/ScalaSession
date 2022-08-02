package scalaSession

object HigherOrderFunction {
  def main(args: Array[String]): Unit = {

    println(s"Sort ${isSorted(List("aa", "bc", "ads", "asdd"), (a: String, b: String) => a.length <= b.length)}")
    //res1: Boolean = true

    println(s"Sort Empty: ${isSorted(List(), (a: Int, b: Int) => a <= b)}")
    //res2: Boolean = true
    println(s"Sort one value: ${isSorted(List(1), (a: Int, b: Int) => a <= b)}")
    //res3: Boolean = true
    println(s"Sort 2 values: ${isSorted(List(2,1), (a: Int, b: Int) => a <= b)}")
    //res4: Boolean = false
    println(s"Sort ${isSorted(List(1,2,3,4), (a: Int, b: Int) => a <= b)}")
    //res5: Boolean = true
    println(s"Sort 1 t0 10: ${isSorted(List(1,2,3,4,5,6,7,8,9,10), (a: Int, b: Int) => a <= b)}")
    //res6: Boolean = true
    println(s"Sort ${isSorted(List(1,2,3,4,1,5,3,2), (a: Int, b: Int) => a <= b)}")
    //res7: Boolean = false


   /* println(s"Sort with function ${isSorted(List(1,9,4,5,10,2,7,8,3,6), comparef)}")
    //res8: Boolean = false
    println(s"Sort with function ${isSorted(List(1,2,3,4,5,6,7,8,9,10), comparef)}")
    //res9: Boolean = true
    println(s"Sort List[String] ${isSorted(List("Scala", "Exercises"), comparef)}")*/
    //res10: Boolean = true

    // Array
    println(s"Sort Array manually ${isSortedArray(Array("Scala", "Exercises"), (x: String, y: String) => x.length < y.length)}")
    //res11: Boolean = true
   /* println(s"Sort Array with Function asc${isSortedArray(Array("Scala", "Exercises"), comparef)}")
    //res12: Boolean = true
    println(s"Sort Array with Function desc ${isSortedArray(Array("Exercises", "Scala"), comparef)}")
    //res13: Boolean = false
    println(s"Sort Arrayyyy ${isSortedArray(Array("Scala", "Exercises"), comparef)}")*/
    //res14: Boolean = true


    println(s"MAXXX: ${findMax(listOfNumber, (a: Int, b: Int) => if(a> b) a else b)}")
    //res15: Int = MAXXX: 9
  }

  // correct the logic here for sort
  def comparef[A](x:A, y:A):Boolean={
    (x, y) match{
      case (x: String, y:String) => x.length < y.length
      case (x: Int, y:Int)  => x < y
      case _ => false
    }
  }

//Correct this Function
  /*
  def sort(ll: List[Int], f:(Int, Int) => Int):Int={
    ll match {
      case h :: Nil => f(h,0)
      //	case h :: m => f(h, m)
      case h :: m :: tail => sort(tail, f)
    }
  }

  val numberList = List(3, 6, 1, 2, 7)
  sort(numberList, (a:Int, b:Int) => if(a<b) a else b)
  */


  def isSorted[A](list: List[A], f:(A, A) => Boolean):Boolean = {
    list match {
      case l :: Nil => true
      case l :: m :: Nil => f(l, m)
      case l :: m :: tail => f(l, m) && isSorted(tail, f)
      case _ => true
    }
  }

  // correct it as per ascending order
   def isSortedArray[A](as: Array[A], f: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
      if (n >= as.length - 1) false
      else if (f(as(n), as(n + 1))) true
      else go(n + 1)

    go(0)
  }


  def findMax[A](list: List[A], f:(A, A) => A): A={
    list match {
      case head::Nil => head
      case head::next::Nil => f(head, next)
      case head::next::tail =>  {
        val aa = f(head, next)
        findMax(aa :: tail, f)
      }
    }
  }

  def findMaxl(list: List[Int]): Int={
    list match {
      case Nil => -1
      case head::Nil => head
      case head::next::tail =>
        val max = if(head > next) head else next
        findMaxl(max :: tail)
    }
  }

  val max = (x: Int, y: Int) => if (x > y) x else y

  def findMaxx(l:List[Int], f: (Int, Int) => Int): Int = l match {
    case Nil => -1
    case h :: Nil => h
    case x :: y :: tail => findMax(f(x, y) :: tail, f)
  }

  val listOfNumber = List(2, 4, 7,9, 3, 5)
  findMax(listOfNumber, (a: Int, b: Int) => if(a> b) a else b)
  findMaxx(listOfNumber, max)

}
package scalaSession
import scala.util.Random

//Extractors
object ExtractorTest extends App {
  object Extractor {
    //Constructor
    def apply(name: String) = s"$name--${Random.nextLong}"

    //Extractor
    def unapply(name: String): Option[String] = {
      val stringArray: Array[String] = name.split("--")
      if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
    }
  }

  val extractor = Extractor("test")  // test--23098234908
  //Pattern Match
  extractor match {
    case Extractor(name) => println(name)  // prints test
    case _ => println("something went wrong")
  }


  val list = List(3, 7, "testPerson", 3.0)

  list.map { x =>
    x match{
      case i:Int => println(s"it's Interger")
      case s:String => println(s"it's String")
      case _ => println(s"default.. ")
    }

  }


}

//another example
/*
trait User {
  def name: String
}
class FreeUser(val name: String) extends User
class PremiumUser(val name: String) extends User

object FreeUser {
  def apply(name:String): FreeUser ={
    if(!name.isEmpty){
      new FreeUser(name)
    }else{
      new FreeUser("Dummy Free User")
    }
  }
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}
object PremiumUser {
  def apply: PremiumUser = new PremiumUser("Dummy Premium user")
  def unapply(user: PremiumUser): Option[String] = Some(user.name)
}

object Test1 extends App {
  /*  println("Scala Start!")

    var name ="tesst123"
    val name1 = "testval"
    def name2 = "testdef"
  */

  val freeUser1 = FreeUser("test1")
  val freeUser2 = FreeUser("")
  val freeUser3 = FreeUser("test3")

  val premiumUser1 = PremiumUser
  val premiumUser2 = PremiumUser
  val premiumUser3 = PremiumUser

  val listofUsers = List(freeUser1, premiumUser1, freeUser2, premiumUser2, freeUser3)

  listofUsers.map { x =>
    x match {
      case FreeUser(s) => println(s"it's free user ${s}")
      case PremiumUser => println(s"it's premium user")
    }
  }
}
*/

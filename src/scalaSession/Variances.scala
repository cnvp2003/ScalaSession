package scalaSession

object Variances extends App {

  sealed trait Currency { def name: String }
  case object EUR extends Currency { val name = "eur" }
  case object USD extends Currency { val name = "usd" }
  case class UnknownCurrency(name: String) extends Currency

 /* var listOfInputs:List[T] = Nil //type AnyVal
  listOfInputs:List[Int] = List(1, 2, 4, 5) //Int
  listOfInputs:List[String] = List("test12", "Person") //String type*/

  //Covariance, Contravariance, Invariance
  class NetherLand[T]
  class USA[+T]()
  class OtherContry[-T]


  class Animal {}
  class Mammal extends Animal{}
  class Dog extends Mammal{}

  /*Box1 is covariant in T
Box[Dog] can be used for Box[Mammal] not Box[Animal]
Only subtypes of Mammal are ok*/
  class Box1[+T]{}

  /*Box2 is contravariant in T
  Box[Animal] can be used for Box[Mammal] not Box[Dog]
  Only supertypes of Mammal are ok*/
  class Box2[-T]{}

  /*Box3 is invariant in T
  Only Mammal is ok*/
  class Box3[T]{}

    def method1(box:Box1[Mammal]){}
    def method2(box:Box2[Mammal]){}
    def method3(box:Box3[Mammal]){}

    /*covariance*/
   // method1(new Box1[Animal]) //compile fails
    method1(new Box1[Mammal])
    method1(new Box1[Dog])

    /*contravariance*/
    method2(new Box2[Animal])
    method2(new Box2[Mammal])
    //method2(new Box2[Dog]) //compile fails

    /*invariance*/
    //method3(new Box3[Animal]) //compile fails
    method3(new Box3[Mammal])
    //method3(new Box3[Dog]) //compile fails
}


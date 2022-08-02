package scalaSession
/*
Null– Its a Trait.
null– Its an instance of Null- Similar to Java null.
Nil– Represents an empty List of anything of zero length. Its not that it refers to nothing but it refers to List which has no contents. Nil is an empty singleton object list that extends the List type

Nothing is a Trait. Its a subtype of everything. But not superclass of anything. There are no instances of Nothing.

None– Used to represent a sensible return value. Just to avoid null pointer exception. Option has exactly 2 subclasses- Some and None. None signifies no result from the method.

Unit– Type of method that doesn’t return a value of anys sort.

Note: Any is supertype of AnyRef and AnyVal. AnyRef is the supertype of all the reference classes (like String, List, Iterable) in scala. AnyVal is the supertype of all the value classes (like Int, Float, Double, Byte, Short..). Null is a subtype of all the reference classes. null is its only instance. Nothing is subtype of every other type i.e of reference and value classes.

Think- AnyRef == Object in Java.*/

object NullNothing {

}

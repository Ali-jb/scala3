// {

abstract class Candy
class Fudge extends Candy
class ChocolateFudge extends Fudge



// // fill in the blanks on the following tests with the expected results. Make sure to
// // understand why these are the case
val mAndMs = "M&Ms"

mAndMs.isInstanceOf[Any] 
mAndMs.isInstanceOf[AnyRef]

mAndMs.isInstanceOf[String]

val mAndMsRef: AnyRef = mAndMs

mAndMsRef.isInstanceOf[String]     // why? Because this is a runtime test!
mAndMsRef.isInstanceOf[AnyRef]
mAndMsRef.isInstanceOf[Any] 


// koan("Fun with top types of values") {
val lots: Any = 1000
val less: Any = 100.0

lots.isInstanceOf[Any] 
lots.isInstanceOf[AnyRef]  //-because it is autoboxed in order to give it instanceOf implicitly :-)
lots.isInstanceOf[Int] 
lots.isInstanceOf[AnyVal]
less.isInstanceOf[AnyVal]

less.isInstanceOf[Any] 
less.isInstanceOf[AnyRef]
less.isInstanceOf[Double] 
less.isInstanceOf[Int] 

val lotsVal: AnyVal = 1000
val lessVal: Any = less

lotsVal.isInstanceOf[Int] 
lessVal.isInstanceOf[Double] 



val chocolateFudge = new ChocolateFudge
val fudge = new Fudge

fudge.isInstanceOf[Any]
fudge.isInstanceOf[AnyRef]
fudge.isInstanceOf[Candy]
fudge.isInstanceOf[Fudge]
fudge.isInstanceOf[ChocolateFudge]

chocolateFudge.isInstanceOf[Any] 
chocolateFudge.isInstanceOf[AnyRef]
chocolateFudge.isInstanceOf[Candy]
chocolateFudge.isInstanceOf[Fudge]
chocolateFudge.isInstanceOf[ChocolateFudge]

fudge.isInstanceOf[AnyVal]
chocolateFudge.isInstanceOf[AnyVal]


// val null1: Null = null

// null1.isInstanceOf[String]
// null1.isInstanceOf[Candy]
// null1.isInstanceOf[Fudge]
// null1.isInstanceOf[ChocolateFudge]

// Why won't either of the the following lines compile
// null1.isInstanceOf[Null]// like above - isInstanceOf is not defined on null, so it can't match
// null1.isInstanceOf[Nothing]  // and again, but this time for Nothing

val null2: String = null
val null3: Candy = null
val null4: Fudge = null
val null5: ChocolateFudge = null

null2.isInstanceOf[String]
null3.isInstanceOf[Candy] 
null4.isInstanceOf[Fudge] 
null5.isInstanceOf[ChocolateFudge] 



// // this will compile (why), but fail with (not surprisingly) an exception when run
// // can you find a way to fix it without removing the code? (Think football!)

def getMeNothing() = throw new IllegalStateException

// intercept[IllegalStateException] {
// val nothing: Nothing = getMeNothing()
//     // can you do anything useful with Nothing?
//     // not really, other than compare it with nothing


val v1 = 100
val v2 = 100
val v3 = 101

(v1 == v2)
(v1 != v3)

val f1 = new Fudge
val f2 = new Fudge
val f3 = new ChocolateFudge

(f1 == f2) 
(f1 eq f2) 
(f1 != f3) 
(f2 ne f3) 

val l1 = List(1,2,3)
val l2 = List(1,2,3)
val l3 = List(2,3,4)

(l1 == l2) 
(l1 eq l2) 
(l1 != l3) 
(l1 ne l3) 


// write a method called listProduct to turn any Product type into a List[Any] with the
// fields from the product in it, then uncomment the following to test the method

def listProduct(x: Product): List[Any] = x.productIterator.toList
// koan("make a list from any product") {
val tup = (1, '2', "three")
listProduct(tup)
//  should be (List(1, '2', "three"))

case class Person(first: String, last: String, age: Int, gender: Char)
val p1 = Person("Sally", "Smith", 43, 'F')

listProduct(p1) 
// should be (List("Sally", "Smith", 43, 'F'))




def checkInstance(obj: Any): Unit = {
  val isAnyVal = obj.isInstanceOf[AnyVal]
  val isAnyRef = obj.isInstanceOf[AnyRef]

  if (isAnyVal && !isAnyRef) {
    println(s"$obj is an instance of AnyVal")
  } else if (!isAnyVal && isAnyRef) {
    println(s"$obj is an instance of AnyRef")
  } else {
    println(s"$obj is neither an instance of AnyVal nor AnyRef")
  }
}

// Testing with different objects
val intObj: Any = 42
val stringObj: AnyRef = "Hello"
val listObj: Any = List(1, 2, 3)

checkInstance(intObj)    // Output: 42 is an instance of AnyVal
checkInstance(stringObj) // Output: Hello is an instance of AnyRef
checkInstance(listObj)   // Output: List(1, 2, 3) is neither an instance of AnyVal nor AnyRef


stringObj.isInstanceOf[AnyRef]
stringObj.isInstanceOf[AnyVal]  


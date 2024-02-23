def fail(message: String): Nothing = 
throw new IllegalThreadStateException

val predResult =true
val x: Int = if predResult then 1 else fail("message")


val flag = true
if flag then 1.0 else ()

// val test = if flag then 1.0
// val test2 = if flag then "hi"
val test3 = if flag then "hi" else null


if flag then 2.0 else fail("message")

//(product with serializable is the default for case classes)
abstract class Fruit extends Product with Serializable
case class Apple(name: String) extends Fruit 
case class Orange(name: String) extends Fruit 

if true then Apple("fiji") else Orange("jaffa")
// look at the type inferences
List(Apple("fiji"), Orange("jaffa"))
List(Apple("fiji"), Apple("granny Smith")) 

//_______________________________________________________________

def sumOf[@specialized(Int, Double, Long) T: Numeric](items: T*): T =
    val numeric = implicitly[Numeric[T]]
    items.foldLeft(numeric.zero)(numeric.plus)

sumOf(1,2,3)

//_______________________________________________________________

val s1: String  = "Hello"
val s2: String  = null

s1.length()
//s2.length error

//but using option
val s3: Option[String] = Some("Hello")
val s4: Option[String] = None

// s3.length() will not compile, instead:
s3.map(_.length)
s4.map(_.length)

//_______________________________________________________________

val numWords = Map(1 -> "one", 2 -> "two", 3 -> "three")

numWords(1)
// numWords(4) error

//built in option in get
val w1 = numWords.get(4)


//now we can pattern match 
w1 match {
    case Some(word) => word
    case None => "Unknown"
}

w1.getOrElse("Unknown")

//_______________________________________________________________
// a never error-causing function for finding the fourth letter of the ith word
def findFourth(i: Int): Option[Char] = 
    for 
        word <- numWords.get(i)
        char <- word.drop(4).headOption
    yield char

findFourth(3)
findFourth(4)
findFourth(5)

//_______________________________________________________________

 // use case classes for equality and hashcodes to work on instances.
//there is also a second option to define custom equality in the class

// only in scala 3 we have strictEquality
// it prevents us to compare two object that are not from the same type
// import scala.language.strictEquality
//_______________________________________________________________

//product types
case class Triplet1(i: Int, ch: Char, str: String)
case class Triplet2(i: Int, ch: Char, str: String)

val triplet1 = Triplet1(1, '2', "three")
val triplet2 = Triplet2(1, '2', "three")

triplet1 == triplet2

triplet1.productIterator.toList
triplet1.productArity
triplet1.productElement(2)
triplet1.productElementName(2)
triplet1.productPrefix

val tup1 = (1, '2', "three")

tup1.productIterator.toList
tup1.productArity
tup1.productElement(2)
tup1.productElementName(2)
tup1.productPrefix

//_______________________________________________________________


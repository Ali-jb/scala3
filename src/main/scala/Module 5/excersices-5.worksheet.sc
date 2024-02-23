import java.io.DataOutput


//   test ("Multiply numbers") {

//     // Uncomment the tests below, then write a local function, mult, to satisfy the tests below.
//     // Remember that local functions can access variables from the method space without the
//     // need to pass them in. The function should multiply the argument passed in by the multiplier var

var multiplier = 3

def mult(n: Int): Int = n * multiplier

val allNumbers = List(0,1,2,3,4,5,6,7)

//     // alter the two filters below to filter only odd and even numbers out of the list respectively,
//     // to make the tests pass.
//     // Just like in Java, % is the modulo operator

val evens = allNumbers.filter(x => x % 2 == 0)
val odds = allNumbers.filter(x => x % 2 != 0)

//     // using placeholder syntax, define a val "mult" that multiplies 2 Ints together, then uncomment
//     // the tests below and make sure they pass

val mult: (Int, Int) => Int = (_ * _)
mult(2,4)

def boundToLimits(lower: Int, v:Int, upper: Int): Int = 
    if v < lower then lower
    else if v > upper then upper 
    else v

boundToLimits(lower = 10, v=15, upper = 90)


// now create a partially applied function from the above called waterAsLiquid with lower bounds of 0
// and upper bounds of 100, but with the middle value (to test) not yet bound (use a placeholder)
// Then uncomment the tests below and make sure they pass

val waterAsLiquid = boundToLimits(lower = 0,_, upper = 100)            
waterAsLiquid(120)
waterAsLiquid(3)
waterAsLiquid(-10)

//     // create a multipleDoubles method to satisfy the tests below, uncomment the tests and run them
//     // to ensure it works

    // multiplyDoubles(1.0, 2.0, 3.0) should be (6.0 +- 0.00001)
    // multiplyDoubles(1.1, 2.2, 3.3, 4.4, 5.5, 6.6) should be (1275.52392 +- 0.00001)
    // multiplyDoubles() should be (1.0)

def multiplyDoubles(numbers: Double*): Double = 
    if numbers.isEmpty then 1 
    else numbers.head *  multiplyDoubles(numbers.tail: _*)



multiplyDoubles(1.0, 2.0, 3.0)
multiplyDoubles(1.1, 2.2, 3.3, 4.4, 5.5, 6.6) 
multiplyDoubles() 



// listOfLists("3","2","1") should give back: List(List("3","2","1"), List("2","1"), List("1"))
def listOfLists(theList: String*): List[List[String]] =
    if theList.isEmpty then Nil
    else theList.toList :: listOfLists(theList.tail: _*)

listOfLists("3","2","1")
listOfLists("Hello", "World")
listOfLists("Hello", "There", "World") 


def opposite(item: String): String = 
    item match
        case "North" => "South"
        case "Hot" => "Cold"
        case "Cool" => "Square"
        case anythingElse => "Not " + anythingElse

opposite("North")
opposite("Hot")
opposite("Cool")
opposite("Hip")
opposite("Funny")


def opposites(direction: String): String = {
  val opposites = Map(
    "North" -> "South",
    "Hot" -> "Cold",
    "Cool" -> "Square"
  )

  opposites.getOrElse(direction, "Not " + direction)
}

// Example usage:
val oppositeDirection = opposites("North")
println(oppositeDirection) // Should print "South"

val oppositeTemperature = opposites("Hot")
println(oppositeTemperature) // Should print "Cold"






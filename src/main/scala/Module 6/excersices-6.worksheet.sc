
// fix the two functions below so that they return true if the list of numbers passed in has any
// odd numbers (for the first functions) and even numbers (for the second function) so that the
// tests pass

// containsOdd(List(2,4,6)) should be (false)
// containsEven(List(1,3,5)) should be (false)
// containsOdd(List(1,2,3)) should be (true)
// containsEven(List(1,2,3)) should be (true)
// containsOdd(Nil) should be (false)
// containsEven(Nil) should be (false)



def containsOdd(numbers: List[Int]): Boolean = numbers.exists(x => x % 2 != 0)
def containsEven(numbers: List[Int]): Boolean = numbers.exists(x => x % 2 == 0)

containsOdd(List(2,4,6)) 
containsEven(List(1,3,5)) 
containsOdd(List(1,2,3)) 
containsEven(List(1,2,3)) 
containsOdd(Nil) 
containsEven(Nil) 


// uncomment the tests below, and then write a new function withFileContents that takes a file name
// as input, opens the file, reads the first line in, and provides it to a function that does something
// with it - the tests below will exercise a couple of options based on files in the working directory
// to make sure your implementation works. Of course, your function should close the file after use.
// For now, you can assume that the function passed in takes a String and returns another String.
// Hint - to make the tests pass, you might need to clean up the string that is read in from the file,
// try .trim()

import java.io.File
import scala.io.Source

val fileDir: String = "/home/ali/code/scalaproject/target" 

val fileLoc = new File(fileDir)
val file = new File(fileLoc, "romeo.shkspr") 

def withFileContents[T](file: File, default: T)(fn: String => T) =
    val source = Source.fromFile(file)
    try
        source.getLines().toSeq.headOption.map{line => fn(line)}.getOrElse(default)
    finally source.close()

withFileContents(file, ""){
    line => line.reverse

}


val second_file = new File(fileLoc, "sum.txt")
withFileContents(second_file, ""){ str =>
      str.split(",").map(_.toInt).reduceLeft(_ + _).toString   // make sure to understand what this is doing
    }


//     // This is a bit of a contrived exercise, but make a function onlyIfTrue that takes a predicate (by-name
//     // function that returns a Boolean), and an operation to carry out if and only if the predicate
//     // is true, otherwise do nothing. For now, assume that the operation takes no arguments and has no
//     // return (Unit).


final def onlyIfTrue(pred: => Boolean)(body: => Unit): Unit = 
    if pred then body

    
val numList = List(-1, 0, -2, 3, -4, 5)

var numberBelowZero = 0
numList.foreach { n => onlyIfTrue(n < 0) {numberBelowZero += 1 } }
numberBelowZero
// numberBelowZero should be (3)


numList.filter(x => x < 0)
numList.filter(x => x < 0).length


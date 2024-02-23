import java.io.File
import scala.io.Source


// bad practice (boilerPlate)
def fileContainsQuestion(file: File): Boolean = 
    val source = Source.fromFile(file)

    try 
        source.getLines().toSeq.headOption.map {line =>
            line.trim.endsWith("?")}.getOrElse(false)
    finally source.close()


def emphasizeFileContents(file: File): String = 

    val source = Source.fromFile(file)

    try 
        source.getLines().toSeq.headOption.map {line =>
            line.trim.toUpperCase}.getOrElse("")
    finally source.close()

// best practice (type parameter)
def withFileContents[A](file: File, fn: String => A, default: A): A = 
    val source = Source.fromFile(file)

    try source.getLines().toSeq.headOption.map{line => fn(line)}.getOrElse(default)
    finally source.close()

val projectDir = "/home/ali/code/scalaproject/target/"
val fileLoc = new File(projectDir)

val file = new File(fileLoc, "romeo.shkspr") 
val result = withFileContents(file, _.trim.toUpperCase(), "")
val result_2 = withFileContents(file, _.trim.endsWith("?"), false)

// ###############################################################################################

//not good paractice
val results_3 = withFileContents(file, {
    line => 
        val letters = line.toLowerCase.filterNot(_ == ' ').toSeq
        val grouped = letters.groupBy(identity)
        grouped.maxBy {case (char, seq) => seq.size }._1
}, 'e')

//better practice
val mostCommonLetter: String => Char = {
line => 
        val letters = line.toLowerCase.filterNot(_ == ' ').toSeq
        val grouped = letters.groupBy(identity)
        grouped.maxBy {case (char, seq) => seq.size }._1
}

withFileContents(file, mostCommonLetter, 'e')

// ###############################################################################################
//best practice (generic loan)

def withFileContentsPlus[A](file: File, default: A)(fn: String => A) : A =
        val source = Source.fromFile(file)

        try source.getLines().toSeq.headOption.map{line => fn(line)}.getOrElse(default)
        finally source.close()

//pay attention that we can use {} instead of () when we only have to pass one parameter to a function
withFileContentsPlus(file, 'e') {line => 
    val letters = line.toLowerCase.filterNot(_ == ' ').toSeq
    val grouped = letters.groupBy(identity)
    grouped.maxBy {case (char, seq) => seq.size }._1 
}

//zero arity function
import scala.util.Random
val makeARandom: () => Double = () => Random.nextDouble()
 makeARandom()

//custom loops
import scala.annotation.tailrec

@tailrec
final def fruitloop_0(pred: () => Boolean)(body: () => Unit): Unit = 
    if (pred()) then 
        body()
        fruitloop_0(pred)(body)

var z = 0
fruitloop_0(() => z < 5) { () =>
    println(z * z)
    z += 1 
}



//byname functions: this is exactly identical too while. my question is: so what?!
@tailrec
final def fruitloop(pred: => Boolean)(body: => Unit): Unit = 
    if pred then 
        body
        fruitloop(pred)(body)

var x = 0
fruitloop(x < 5) {
    println(x * x)
    x += 1 
}


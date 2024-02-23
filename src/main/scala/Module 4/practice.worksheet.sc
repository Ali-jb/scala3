val x: Int = 1 + 2
val y: Unit = println(x)
val un = println(x)
un  == ()
println(y) 

// ------------------------------------------------------------------------------------------------------------

import java.io.PrintWriter
import java.io.File

class WriterOutput(writer: PrintWriter):
    def write(s: String): Unit = writer.println(s)

val ex1 = new PrintWriter(new File("ext.txt"))
val out1 = new WriterOutput(ex1)

out1.write("Hello")
out1.write("to")
out1.write("you")
out1.write("mothaf***a")

ex1.close()

// ------------------------------------------------------------------------------------------------------------

class WriterOutput2(writer: PrintWriter):
    def write(s: String): WriterOutput2 =
         writer.println(s)
         this
    def close(): Unit = writer.close()

val ex2 = new PrintWriter(new File("ex2.txt"))
val out2 = new WriterOutput2(ex2)
out2.write("Hello").write("to").write("You").write("mothaf***a").close()

// ------------------------------------------------------------------------------------------------------------

class WriterOutputMod(file_name: String):
  private val writer: PrintWriter = new PrintWriter(new File(s"$file_name.txt"))

  def write(s: String): WriterOutputMod = 
    writer.println(s)
    this

  def close(): Unit = writer.close()

object WriterOutputMod:
    def apply(file_name: String): WriterOutputMod = new WriterOutputMod(file_name)

val out = WriterOutputMod("test_file")
out.write("hello").write("there").close()

// ------------------------------------------------------------------------------------------------------------

def greet(n: Int): Unit = 
    var i = 0
    while (i<n)
        println("Hello")
        i += 1
greet(8)


def greet_new(n: Int): Unit = 
    var i = 0
    while i<n do 
        println("Hello")
        i += 1
greet(8)


final def greet_best_practice(n: Int, curr: Int = 0): Unit = 
    if curr < n then
        println("Hello")
        greet_best_practice(n, curr + 1)

greet_best_practice(3)

// ------------------------------------------------------------------------------------------------------------

val args = Array.empty[String]

val filename2 =
    try 
        args.head
    catch
        case _: NoSuchElementException => "default.txt"
    finally //this is side effecting
        println("whee")

// ------------------------------------------------------------------------------------------------------------

for i <- 1 to 10 do println(i)
//the code above translates into:
(1 to 10).foreach(i => println(i))

for
     i <- 1 to 3
     j <- 1 to 3
do
    println(i*j)

// ------------------------------------------------------------------------------------------------------------
//no side effects
for i <- 1 to 10  yield i
//the code above translates into:
(1 to 10).map(i => i)

// for in scala 2: put parantheses and remove do

for
     i <- 1 to 3
     j <- 1 to 3
     k <- 1 to 3 
yield 
    i * j * k
//the code above translates into:
(1 to 3).flatMap(i => (1 to 3).flatMap(j => (1 to 3).map(k => i * j * k)))
// all for loops translate into flatMaps except the last one which translates into map 

// ------------------------------------------------------------------------------------------------------------

// val forLineLengths = 
//     for 
//         file <- filesHere
//         if file.getName.endsWith(".sc")
//         line <- fileLines(file)
//         trimmed = line.trim 
//         if trimmed.matches(".*for.*")
//     yield trimmed.length

// ------------------------------------------------------------------------------------------------------------
// intro to asynchronous programming
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global

val f1 = Future(1.0)
val f2 = Future(2.0)
val f3 = Future(3.0)

val f4 = for 
    v1 <- f1
    v2 <- f2
    v3 <- f3
yield v1 + v2 + v3 

Await.result(f4, 10.seconds)

// ------------------------------------------------------------------------------------------------------------
//match exoression
val test_value = 1
test_value match
    case 1 => println("it's one")
    case 2 => println("it's two")
    case _ => println("it's something else")
end match
 

val test_value_2 = 1
test_value_2 match
    case 1 => "one"
    case 2 => "two"
    case _ => "something else"
end match

val test_value_3 = -1

test_value_3 match
    case 0 => "it's a zero"
    case v if v > 0 => s"it's positive $v"
    case v  => s"it's negative ${v.abs}"

def matchIt(x: Any):String = 
    x match
        case "Hello" => "Well, Hello back!"
        case 1::rest => s"A list beginning with 1, rest is $rest"
        case Nil => "The empty list"
        case 5 => "The number five"
        case _: List[_] => "Some kind of list, not empty and not starting with 1"
        case x => s"It's something else: $x"

matchIt(1)
matchIt(List(1,2,3))
matchIt(List(2,3))
matchIt(List(1))
matchIt(Nil)
matchIt("Hello")
matchIt("hello")

// ------------------------------------------------------------------------------------------------------------


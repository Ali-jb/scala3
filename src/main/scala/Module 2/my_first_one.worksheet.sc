//MOD1
var x = 1
while (x < 10) {
    println(s"the square of $x is ${x * x}")
    x += 1
}



// don't use
//var x = 1

//while x < 10 do
//    println(s"the square of $x is ${x * x}")
//    x+=1

// MOD2
def sayhi(name:String):Unit = println(s"Hello $name!")
sayhi("bozghaleh")

for (i <- 1 to 10) yield i * 2
for  i <- 1 to 10  yield i * 2


for (i <- 1 to 10) {println(i)}

for i <- 1 to 10 do println(i)


var a = 1
val b = a = 10
println(a)
println(b)

def addition(x:Int, y:Int): Int = x + y 
addition(2,3)


def sumAndDifference(a:Int, b:Int): (Int, Int) = 
    val sum = a + b
    val difference = a - b
    (sum, difference)

var sumAndDifference_res = sumAndDifference(9,4)
sumAndDifference_res._1
sumAndDifference_res._2

val (sm, dif) = sumAndDifference(9,4)


val array_1: Array[Int] = Array(1,2,3)
def squareRoot(inputs:List[Int]): List[Double] =
    for (x <- inputs) yield math.sqrt(x)
squareRoot(List(1,2,3,4,5,6,7,8,9))

val new_list_1 = List(1,2,3)
val new_list_2 = 1 :: 2 :: 3 :: Nil
// nil is an empty list

val new_list_3 = new_list_1 ::: new_list_2
val new_list_4 = new_list_1 :: new_list_2

def sumThemAll(input : Seq[Int]):Int = input.sum
sumThemAll(new_list_1)
val new_set_1 = Set(1,2,3,4,4,5,7)
sumThemAll(new_set_1.toSeq)

val businessPlan = Map(
    1 -> "steal underpants",
    2 -> "???",
    3 -> "profit",
    4 -> "test"
)

for ((step, instruction) <- businessPlan) do 
    println(s"Step $step - $instruction")

import scala.io.Source
for (line <- Source.fromFile("/home/ali/code/scalaproject/src/main/scala/Module 2/sample.txt").getLines()) do 
    println(line)

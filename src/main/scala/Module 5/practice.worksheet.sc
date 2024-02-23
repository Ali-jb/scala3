import scala.annotation.tailrec

// class FactSeq:

//     def factSeq(n: Int): List[Long] = 
//             factSeqInner(n, List(1L), 2)



//     @tailrec
//     private def factSeqInner(n: Int, acc: List[Long], ct: Int): List[Long] = 
//         if ct > n then acc else
//             factSeqInner(n, ct * acc.head :: acc, ct+1)

// FactSeq.factSeq(7)


// // private method
// object FactSeq:

//     def factSeq(n: Int): List[Long] = 
//             factSeqInner(n, List(1L), 2)

//     @tailrec
//     private def factSeqInner(n: Int, acc: List[Long], ct: Int): List[Long] = 
//         if ct > n then acc else
//             factSeqInner(n, ct * acc.head :: acc, ct+1)

// FactSeq.factSeq(8)


// // nested method (we can use n in neseted methods without passing it again)
// object FactSeqNested:

//     def factSeq(n: Int): List[Long] = 
//         @tailrec
//         def factSeqInner(acc: List[Long], ct:Int): List[Long] = 
//             if ct > n then acc else 
//                 factSeqInner(ct * acc.head :: acc, ct + 1)
                
//         factSeqInner(List(1L), 2)


// FactSeqNested.factSeq(8)

// -----------------------------------------------------------------------------------------------------------------------

// function literals
def multiplyMethod(a: Int, b: Int): Int = a * b
multiplyMethod(2,3)

val multiplyFunctionLiteral: (Int, Int) => Int = (a, b) =>  a * b
multiplyFunctionLiteral(2,3)

// anonymous function literals 
val nums = (1 to 5).toList

nums.map(x => x * x)
nums.map(x => x + x)
nums.map(x => x % 2 == 0)



val myadd: Function2[Int, Int, Int] = (a: Int, b: Int)  => a + b

myadd(2,3)

val myaddCurried = myadd.curried
myaddCurried(2)(3)

val myaddTupled = myadd.tupled
myaddTupled((2,3))


// -----------------------------------------------------------------------------------------------------------------------

//function order (Higher order functions)

// defining a higher order function
def compareNeighbours(xs: List[Int], cmp: (Int, Int) => Int): List[Int] = 
   for pair <- xs.sliding(2).toList yield
    cmp(pair(0), pair(1))

val numbers: List[Int] = (1 to 10).toList
numbers.sliding(2).toList

compareNeighbours(numbers, (a,b) => a + b)

// -----------------------------------------------------------------------------------------------------------------------
// paceholders

val adder: (Int, Int) => Int = _ + _
adder(2,3)

val adder2 = (_: Int) + (_: Int)
adder2(2,3)

compareNeighbours(nums, _ * _)

// -----------------------------------------------------------------------------------------------------------------------
//partially applied functions

def add(a: Int, b: Int, c:Int) = a + b + c
add(1,2,3)
val addp1 = add(1, _, _)
addp1(2,3)

val addfunction = add // _
addfunction(1,2,3)

def addv2(a:Int, b:Int) = a + b
val addv2function = addv2 
compareNeighbours(nums, addv2)

// -----------------------------------------------------------------------------------------------------------------------
// closures
 val incBy1 = (x: Int) => x + 1
 val more = 10
 val incByMore = (x: Int) => x + more

incBy1(1)
incByMore(1)

// -----------------------------------------------------------------------------------------------------------------------
//partial function
val pf1: PartialFunction[Int, Int] = 
    case x: Int if x >= 0 => x*x
    case x: Int if x < 0  => x - 1

pf1(2)
pf1(-2)
pf1.isDefinedAt(0)
(0 to 5).map(pf1)

// -----------------------------------------------------------------------------------------------------------------------
val pf2: PartialFunction[Int, Int] = 
    case x: Int if x >= 0 => x*x
(-5 to 5).collect(pf2)

// -----------------------------------------------------------------------------------------------------------------------
//var args (* = any number of type)
def makeList(x: Int*): List[Int] = x.toList
makeList()
makeList(1,2,3)

// -----------------------------------------------------------------------------------------------------------------------


def greet(greeting: String, names: String*) : Seq[String] = 
    for name <-names yield s"$greeting $name!"
    
val names = List("Asghar", "Ahamd", "Bahman")
greet("hi", "Asghar", "Ahamd", "Bahman")
// greet("hi", names)
greet("hi", names: _*)

//named parameters
greet(greeting = "hello", names = names: _*)
// greet(names = names: _*, greeting = "hello")
greet(names = "Asghar", "Kokab", greeting = "fuck you")
//defualt parameters: use = in function definition


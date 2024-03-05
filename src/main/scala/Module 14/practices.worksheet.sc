import scala.compiletime.ops.double
import scala.collection.mutable
import scala.collection.immutable

def popImmutableQueue(q: immutable.Queue[Int]): (Int, immutable.Queue[Int]) = 
    q.dequeue


def popMutableQueue(q: mutable.Queue[Int]): Int = 
    q.dequeue()

val imQ = immutable.Queue(1,2,3,4)
val mQ = mutable.Queue(1,2,3,4)

popImmutableQueue(imQ)
popMutableQueue(mQ)

// ____________________________________________________________________________

val q = immutable.Queue(1,2,3,4)
val eq = immutable.Queue.empty[Int]

val xs = List(1,2,3,4)
val exs = List.empty[Int]

val v = Vector(1,2,3,4)
val ev = Vector.empty[Int]

val s = Set(1,2,3,4)
val es = Set.empty[Int]

val arr = Array(1,2,3,4l)

q == xs
q == v
xs == v

// xs == s
// xs == arr
xs == arr.toIndexedSeq
xs == arr.toList


xs.toVector
xs.toArray
xs.toSet

// xs.toMap
xs.zipWithIndex.toMap

// xs.toQueue
xs.to(immutable.Queue)

// ____________________________________________________________________________

val vowels = Set('a', 'e', 'i', 'o', 'u')

vowels.contains('a')
vowels.contains('t')

vowels.apply('a') //equivalent to set.contains
vowels('t')

"happyone".filter(vowels)

vowels + 'y'
vowels + 'e'

val commonLetters = Set('e', 't', 'a', 'o', 'i', 'n', 's', 'r', 'h')

commonLetters.intersect(vowels)
commonLetters diff vowels
commonLetters union vowels

"hello to me".count(vowels)


immutable.TreeSet('u', 'o', 'i', 'e', 'a') //sorted

val vowelsMut = mutable.Set('a', 'e', 'i', 'o', 'u')
vowelsMut += 'y'
vowelsMut

vowelsMut -= 'y'
vowelsMut

vowelsMut('y')

vowelsMut('y') = true
vowelsMut

// vowelsMut('y') = false
// vowelsMut

// ____________________________________________________________________________

val numWords = Map(1 -> "one", 2 -> "two", 3 -> "three", 4 -> "four", 5 -> "five")
numWords(1)  //it's not best practice
// numWords(6)

numWords.get(1)
numWords.get(6)

numWords.getOrElse(6, "?")

val nums = List(1,2,3,4,5)
nums.map(numWords)

for (num, word) <- numWords do 
    println(s"$num -> $word") //it does not maintain order

val tm = immutable.TreeMap.empty[Int, String] ++ numWords
//treeMaps maintain sort-by-key order

val mm = mutable.Map.empty[Int, String] ++ numWords

mm -= 2
mm += 2 -> "two"


numWords.keys
numWords.keySet
numWords.values

numWords.view.filterKeys(_ % 2 == 0).toMap
numWords.view.mapValues(_.reverse).toMap

numWords.transform { case (k, v) => s"$v($k)"}
numWords.map(_.swap)

val evens = (for (i <- 1 to 5) yield i -> (i % 2 == 0)).toMap
evens.map(_.swap) //be aware of data loss when swapping!

// ____________________________________________________________________________

val numbers = List.range(1, 21)
val numbersIter = numbers.iterator

numbersIter.hasNext

// do not do this!
// if numbersIter.length > 0 then numbersIter.next()

// do this instead
if numbersIter.hasNext then numbersIter.next() else 0
//or this
if numbersIter.nonEmpty then numbersIter.next() else 0

// ____________________________________________________________________________

val vec = Vector.range(10, 30)

val vecView = vec.view

def calcSquare(x: Int): Int = 
    println(s"Calculating for $x")
    x * x

val squaresView = vecView.map(calcSquare)

squaresView(2)
squaresView(4)
squaresView(2)

val squares = squaresView.toIndexedSeq // lazy collections!

squares(2)
squares

// ____________________________________________________________________________

//it is better to use lazy lists instead of views.
import scala.collection.immutable.LazyList

val numsFromOne = LazyList.from(1)
// lazy lists are potentially infinite and have a function as their last element.

println(numsFromOne)
println(numsFromOne.head)
println(numsFromOne(2))

val firstTenNums = numsFromOne.take(10).toList

LazyList.from(1).zip(LazyList.from(2)).take(5).toList

val factorial: LazyList[BigInt] = 
    BigInt(1) #:: factorial.zip(LazyList.from(2)).map{ case (a, b) => a * b}
val firstTenFactorial = factorial.take(10).toList

val fibs: LazyList[BigInt] = 
    BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map{ case(x, y) => x + y}
fibs.take(10).toList

def fibs2(first: BigInt = 0, second: BigInt = 1): LazyList[BigInt] = 
    val num = first + second
    num #:: fibs2(second, num)

fibs2().take(10).toList
fibs2().take(100000000) //safe to run becuase it's has not been calculated yet :))

// ____________________________________________________________________________

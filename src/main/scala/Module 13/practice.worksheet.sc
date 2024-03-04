val oneTwo = List(1,2)
val threeFour = 3 :: 4 :: Nil
val oneTwoThreeFour = oneTwo ::: threeFour

oneTwoThreeFour.size

// Lists are covariant
class Animal
class Dog extends Animal
class Cat extends Animal

val dogs: List[Dog] = List(new Dog, new Dog)
val animals: List[Animal] = dogs // Covariant assignment 

// other ways to initialize lists
List.fill(5)(0)

List.tabulate(5) // pass a function to apply to 0 to 5:
List.tabulate(5)(x => x * x)
List.tabulate(5)(x => x + 1)

List.range(1, 5)

(1 to 5).toList

(1 until 5).toList

val l1: List[Matchable] = "hello" :: 1 :: true :: Nil
 
// every collection can be converted to list with .toList 

//constant type operations: In computer science and programming, constant time refers to the characteristic of an algorithm or operation where the time it 
//  takes to execute remains constant, regardless of the size of the input. In the context of Scala, a constant time operation typically means that the execution
//  time of that operation does not depend on the size of the data being processed.
val nums = (1 to 10).toList
nums.head
nums.tail
nums.isEmpty
nums.nonEmpty

0 :: nums
nums.::.apply(0)

// linear time operations
val nums2 = (1 to 5).toList
nums2.last
nums2.init
nums.length 
nums2.reverse

//order-based operations
nums(3) //or nums.apply(3)
nums.drop(3)
nums.take(3)

nums.drop(3).headOption
nums.drop(20).headOption

nums.lift(3)
nums.lift(20)


nums.updated(3, 0)

// replace multiple values
val nums_new = nums.zipWithIndex.map {
      case (element, index) => if (index == 0 || index == 2 || index == nums.length - 1) 0 else element
    
}


// alternative method
val indicesToReplace = List(0, 2, nums.length - 1)
val nums_newer = indicesToReplace.foldLeft(nums) { (acc, index) =>
acc.updated(index, 0)
}

// alternative method
val nums_newest = List.tabulate(nums.length) { index =>
  if (indicesToReplace.contains(index)) 0 else nums(index)
}

//higher order functions
val words = List("four", "four", "char", "word", "felicita")
words.map(_.reverse)

words.reverse.map(_.reverse)

words.map{ word => word.toList} //function literal syntax
words.map(_.toList) 

words.map{ word => word.toList }.flatten
words.flatMap{ word => word.toList}

words.foreach{ word => println(word)}
words.foreach(println)
words foreach println

words.map(_.length)

words.filter(_.contains("a"))
words.filter(_.startsWith("c"))


words.find(_.contains("a"))

words.filter(_.startsWith("z"))
words.find(_.startsWith("z"))

words.indexWhere(_.contains("c"))
words.indexWhere(_.contains("z"))

words.lastIndexWhere(_.contains("o")) //linear time operation

words.filterNot(_.contains("a"))
words.filter(word => !word.contains("a"))

words.partition(_.contains("a")) //filter and filterNot at the same time

words.dropWhile(_.contains("f")) //it stops as soon as the condition is not met
words.dropWhile(_.contains("f")) //it stops as soon as the condition is not met

//folds
val numbers = List(2, 3, 5, 8, 13, 21)

nums.foldLeft(0)((a, b) => a + b)
nums.foldLeft(1)((a, b) => a + b) //(starting_value)(function literal)
nums.reduceLeft(_ + _) //it doesn't have a initialization value
nums.sum

nums.foldLeft(1)(_ * _)
nums.product

//these methods are depricated
(0 /: nums)(_ + _)

(nums :\ 0)(_ + _)

words.foldLeft("")(_ + "," + _)
words.foldRight("")(_ + "," + _) //fold right is less efficient

words.toString()
words.mkString
words.mkString("[",",","]")

//sorting
case class Person(name: String, age: Int)
val people = List(Person("Harry", 25), Person("Sally", 23), Person("Fred", 31))

people.sortWith((p1, p2) => p1.age < p2.age)
people.sortBy(_.age).reverse

given Ordering[Person] = (x: Person, y: Person) =>
  if x.name == y.name then x.age - y.age
  else if (x.name > y.name) 1
  else -1

people.sorted

//more functions
val matrix = List(List(1,2,3), List(4,5,6), List(7,8,9))
matrix.transpose

matrix.flatten
matrix.flatten.sum

trait Fruit extends Product with java.io.Serializable
case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit

val fruits = List(Apple("fiji"), Orange("jaffa"), Apple("Cox's"))

fruits.collect {
  case a: Apple => a
}


words.groupBy(_.head)

words.groupBy(_.head).view.mapValues(_.size).foreach(println)

val xs = List.range(-5, 5)

xs.filter(_ >= 0).map(x => (x * x).toString)

//alternatively
xs.collect{
  case n if n>=0 => (n * n).toString
}

//_________________________________________________________________________________

nums.grouped(3).take(4).toList
nums.sliding(3,1).toList
nums.combinations(3).toList
nums.combinations(3).toList.length
nums.permutations.take(2).toList

val numsPlusOne = nums.map(_ + 1)
nums.corresponds(numsPlusOne)((a, b) => a + 1 == b)

//_________________________________________________________________________________

val chars = List.range('a', 'h')

val idx = chars.indices

chars.zip(idx)

//alternatively
chars.zipWithIndex

for 
  (char, idx) <- chars.zipWithIndex
do
  // println(s"char is $char with index $idx")
  println(s"$idx -> $char")

chars.zipWithIndex.map(_.swap).toMap


words.zip(nums.take(4))

words.zipAll(nums, "None", -1)
val zipped = words.zipAll(nums, None, -1)
val (unzippedWords, unzippedNums) = zipped.unzip

//_________________________________________________________________________________

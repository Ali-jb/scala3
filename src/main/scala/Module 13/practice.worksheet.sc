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

//
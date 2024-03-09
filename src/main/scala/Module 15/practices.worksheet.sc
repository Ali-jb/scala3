//examples of java in scala 

case class Person(name: String, age: Int)

import java.util.{Arrays, Comparator}
val javaArray = Array(
    Person("Marry", 25),
    Person("Sally", 22),
    Person("Tim",33),
)
val comp1 = new Comparator[Person]:
    override def compare(o1: Person, o2: Person) =  o1.age - o2.age

val comp2: Comparator[Person] = (o1: Person, o2: Person) => o1.age - o2.age

Arrays.sort(javaArray, comp1)
javaArray.toList

Arrays.sort(javaArray, comp2)
javaArray.toList

//___________________________________________________________________

val jl = new java.util.ArrayList[Int]
jl.add(1)
jl.add(2)
jl.add(3)

// jl.map(_ * 2) //we don't have .map in java arrays

import scala.jdk.CollectionConverters._
jl.asScala.map(_ * 2)
1
jl.asScala.asJava

def doNothing(xs: java.util.List[Integer]): java.util.List[Integer] = xs 

val x: java.util.List[Int] = jl
x.asScala.map(i => Integer.valueOf(i)).asJava

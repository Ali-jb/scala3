trait Car:
    def color: String
    def describe: String = s"$color colored car"

val c1 = new Car: 
    val color = "red"

c1.describe
//  _______________________________________________________________

 class ActualCar(val color: String, val name: String) extends Car 

 val ac1 = new ActualCar("black", "Model T")
 ac1.describe
 
 val cartest1: ActualCar = ac1
 cartest1.describe

 //this is funny!
 val cartest2: Car = ac1
 cartest2.describe


//  _______________________________________________________________

class Demo extends Car with Function1[String, String]:
    override def color: String = "red"
    override def apply(v1: String): String = s"$v1 $color"
    
val demo = new Demo
demo("cherry")

val descriptionLength = demo.andThen(_.length)
descriptionLength("cherry")

//  _______________________________________________________________
// we can extend iterable trait and get all of the collection APIs for free

class SomeIterable[T] extends Iterable[T]:
    override def iterator: Iterator[T] = ???


// val myIterator = new SomeIterable
// myIterator. (test it)

//  _______________________________________________________________
//a dangerous problem with traits -> we use trait parameters to avoid it,
// we used lazy vals before scala 3
// never have a value to calculated that depends on a abstract value to be overriden later,
//if you had to, use lazy val or trait parameter

// trait Doubler:
//     val num: Int
//     val double: Int = num + num

// class DoubleIt extends Doubler:
//     val num = 2

// val db1 = new DoubleIt
// db1.double

//using trait paramter(only in scala 3)
trait doubler(val num: Int):
    lazy val double: Int = num + num

class DoubleIt extends doubler(2)

val db1 = new DoubleIt
db1.double

//  _______________________________________________________________
//traits with type parameters
trait CompareAge[T]:
    def older(item: T): T
    def younger(item: T): T

def getOlder[T <: CompareAge[T]](item1: T, item2: T): T = 
    item1.older(item2)

def getYounger[T <: CompareAge[T]](item1: T, item2: T): T = 
    item1.younger(item2)
//  ___________________________

case class VintageCar(make: String, model: String, year: Int)
    extends CompareAge[VintageCar]:

    def older(other: VintageCar): VintageCar = 
        if this.year < other.year then this else other 

    def younger(other: VintageCar): VintageCar = 
        if this.year > other.year then this else other 

getOlder(
    VintageCar("ford", "mustang", 1965),
    VintageCar("ford", "model T", 1922)
)

getYounger(
    VintageCar("ford", "mustang", 1965),
    VintageCar("ford", "model T", 1922)
)


case class Person(name: String, age: Int) extends CompareAge[Person]:
    override def older(other: Person): Person = 
        if this.age < other.age then other else this

    override def younger(other: Person): Person = 
        if this.age > other.age then other else this

getOlder(Person("Fred", 25), Person("Jill", 28))
getYounger(Person("Fred", 25), Person("Jill", 28))

//  ___________________________
//they  used it in scala:
val people = List(Person("Fred", 25), Person("Jill", 28), Person("Sally", 22))
given Ordering[Person] = (x, y) => x.age - y.age
people.sorted

//  ___________________________

//selfless traits




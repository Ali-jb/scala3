import java.time.LocalDate
class Person(name: String, age: Int):
    def isAdult: Boolean = age>=21  



// val p1 = new Person("Dave", 18)
// val p2 = new Person("Jill", 25)

// p1.isAdult
// p2.isAdult

object Person:
    def apply(name: String, age: Int): Person = new Person(name, age)

val p1 = Person("Dave", 18)
val p2 = Person("Jill", 25)

p1.isAdult
p2.isAdult

// _______________________________________________________________________
//without override
// abstract class Car(make: String, model: String, year: Int):
//     def isVintage: Boolean = LocalDate.now.getYear - year > 20
    
// //error
// // val c1 = new Car("ford", "mustange", 1965)

// //not error
// val c1 = new Car("ford", "mustange", 1965){}

// _______________________________________________________________________

//with override
abstract class Car(make: String, model: String, year: Int):
    def isVintage: Boolean = LocalDate.now.getYear - year > 20
    def isOld: Boolean
//error
// val c1 = new Car("ford", "mustange", 1965)

//not error 
val c1 = new Car("ford", "mustange", 1965):
    override def isOld: Boolean = true
// this is not good practice because isVintage can lead to differenct results. functions must always return the same result. so we can express it as val 
// _______________________________________________________________________
 // val (uniform access) we can also put protected before val to make it not accessible outside the class
 abstract class Car_val(
    val make: String,
    val model: String,
    val year: Int
):

    val isVintage: Boolean //this won't change for each instance

val c1_val = new Car_val("ford", "mustange", 1965):
    override val isVintage: Boolean = LocalDate.now.getYear - year > 20

// lazyval (best practice) (uniform access)
abstract class Car_lazyval(
    val make: String,
    val model: String,
    val year: Int
):
    lazy val isVintage: Boolean

val c1_lazyval = new Car_lazyval("ford", "mustange", 1965):
    lazy val isVintage: Boolean = LocalDate.now.getYear - year > 20
// _______________________________________________________________________
// so what is a lazyval?

class Demo:
    val a: Int = 
        println("evaluating a")
        10

    def b: Int = 
        println("evaluating b")
        20

    lazy val c: Int = 
        println("evaluating c")
        30

val demo = new Demo
demo.a

demo.b
demo.b

demo.c
demo.c

// _______________________________________________________________________
// inheriting -> we have a hierarchy here: food => fruit => Orange => jaffa (an instance of Orange)
abstract class Food:
    def name: String

abstract class Fruit extends Food

class Orange(val name: String) extends Fruit

val jaffa = new Orange("Jaffa")
jaffa.name
// _______________________________________________________________________


abstract class Vehicle(val name: String, val age: Int):
    override def toString: String = s"$name, $age years old."


class Car_h( //i called it call_h as call_hierarchy because we defined a Car class before in this worksheet.
    override val name: String,
    val make: String,
    val model: String,
    override val age: Int
)  extends Vehicle(name, age):
    override def toString: String = s"a $make $model, named ${super.toString}"
    def asString: String = super.toString


val c1_h = new Car_h("sally","ford", "mustang", 1965)
c1_h.toString
c1_h.asString

// _______________________________________________________________________
// final

// without final (no error)
// class Authority:
//     def theWord: String = "This is the final word on this matter!"

// val sample = new Authority
// sample.theWord


// class Argument extends  Authority:
//     override def theWord: String = "No!"

// val sample_2 = new Argument
// sample_2.theWord

// with final (error)
// class Authority:
//     final def theWord: String = "This is the final word on this matter!"

// val sample = new Authority
// sample.theWord


// class Argument extends  Authority:
//     override def theWord: String = "No!"

// val sample_2 = new Argument
// sample_2.theWord

// using final in the whole class (class would not extend)
// final class infinity
// class beyond extends infinity

// _______________________________________________________________________

// case classes are fun!
case class Car_case(make: String, model: String, year: Int):
    lazy val isVintage: Boolean = LocalDate.now.getYear - year > 20

// auto factory method and nice toString method!
val mustang = Car_case("Ford", "Mustang", 1965)

// no need to put val when defining parameters!
mustang.make
mustang.model
mustang.year
mustang.isVintage 

// consitency even in hash code!
mustang == Car_case("Ford", "Mustang", 1965)
mustang == Car_case("Ford", "Mustang", 1955)

// _______________________________________________________________________





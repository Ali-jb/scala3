// trait Car:
//     def color: String
//     def describe: String = s"$color colored car"
//     override def toString(): String = describe

// trait Classic extends Car:
//     def vintage: Int 
//     override def describe: String = 
//         s"vintage $vintage ${super.describe}"

// trait Convertible extends Car:
//     def poweredTop: Boolean
//     override def describe: String =
//         val top = 
//             if (poweredTop) then "powered convertible" else "convertible"
//         s"$top ${super.describe}"

// class ClassicConvertible(
//     val color: String, val vintage: Int, val poweredTop: Boolean
// ) extends Car with Classic with Convertible


// val c1 = new ClassicConvertible("red", 1965, false)
// c1.describe



//second example
abstract class Car:
    def color: String
    def describe: String = s"$color"
    override def toString(): String = s"$describe car"

trait Classic extends Car:
    def vintage: Int
    override def describe: String = 
        s"vintage $vintage ${super.describe}"

trait Convertible extends Car:
    override def describe: String = 
        s"convertible ${super.describe}"

trait PoweredConvertible extends Convertible:
    override def describe: String =
        s"powered ${super.describe}"

trait HardtopConvertible extends Convertible:
    override def describe: String = 
        s"hard-top ${super.describe}"


class ClassicConvertible(val color: String, val vintage: Int)
    extends Car with PoweredConvertible with Classic with HardtopConvertible

new ClassicConvertible("red", 1986)

class ClassicConvertible2(val color: String, val vintage: Int)
    extends Car with Classic with PoweredConvertible with HardtopConvertible
//hard-top powered convertible vintage 1986 red car
new ClassicConvertible2("red", 1986)

class ClassicConvertible3(val color: String, val vintage: Int)
    extends Car with PoweredConvertible with HardtopConvertible with Classic

new ClassicConvertible3("red", 1986)



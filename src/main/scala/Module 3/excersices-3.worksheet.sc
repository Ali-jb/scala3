// Flight 03 exercise - uncomment the tests below, and then define a class
// called ComplexNum that:
// 1. Has class parameters real and imaginary
// 2. an auxilliary constructor that takes just a real double and creates a
//    complex number with a 0 imaginary value
// 3. has an overridden toString method that prints out the number in the form
//    "<real> + <imaginary>i", e.g. 2.0 + 8.1i
// 4. has a + method that creates a new complex number with a real
//    part containing the sum of the real parts, and an imaginary part containing
//    the sum of the imaginary parts
// 5. has a second overloaded + method that creates a new complex number by taking
//    a double and adding the double to the real part
//
// The tests below should exercise all of these requirements although they
// are far from exhaustive

import scala.compiletime.ops.double
import scala.math.abs



class ComplexNum(val r:Double,val i:Double):
 
  def this(r:Double) = this(r, 0.0)
  override def toString: String  = if (i>0) 
  then  s"$r + ${i}i"
  else  s"$r - ${abs(i)}i"
  
  def +(other: ComplexNum): ComplexNum =
     new ComplexNum((this.r + other.r), (this.i + other.i))
  end +

  def + (real: Double): ComplexNum = 
    new ComplexNum(this.r +   real, this.i)
  end +

  

end ComplexNum


object ComplexNum:
  def apply(i : Double, j : Double) = new ComplexNum(i, j)

  implicit def apply(i : Double): ComplexNum = new ComplexNum(i) 
end ComplexNum


// TESTS
ComplexNum(1,2)
ComplexNum(1,2) + ComplexNum(2,3)
ComplexNum(1,2) + 3

val complex_test_1 = new ComplexNum(4, 2)

val comple_test_2 = new ComplexNum(6.2, -1.5)

val complex_test_3 = new ComplexNum(-3.2)

val complex_test_4_1 = new ComplexNum(6, 3)
val complex_test_4_2 = new ComplexNum(5.4, 7.8)

val complex_test_5_1 = new ComplexNum(6, 3)
val complex_test_5_2 = new ComplexNum(5.4, 7.8)
val complex_test_5_3 = complex_test_5_1 + complex_test_5_2

val complex_test_6 = new ComplexNum(6.5, 3.2) + 5.5

// Extra credit - numbers with a negative imaginary part should be output
// as 6.0 - 5.0i instead of 6.0 + -5.0i - if you have time, write a new test
// for this outcome, and then adapt the toString in the class to work correctly
// Hint: scala.math.abs will give the absolute value of a double

val complex_test_7_1 = new ComplexNum(5, -6)
val complex_test_7_2 = new ComplexNum(5.5, -6.6)

// Extra extra credit - adding a double to a complex works, but adding a complex
// to a double does not. If you add an implicit conversion you can make this work
// if you have time, write a test, and add an implicit to make it work

new ComplexNum(3.4, 4.5) + 5.6 
5.6 + new ComplexNum(3.4, 4.5) 










//  class ComplexNum(r: Double, i: Double) {
    // val real: Double = r
    // val imaginary: Double = i
    // def this(r: Double) = this(r, 0.0)

    // override def toString(): String = "" + real +
    //   (if (imaginary < 0.0) " - " else " + ") + math.abs(imaginary) + "i"

    // def +(other: ComplexNum): ComplexNum = new ComplexNum(real + other.real, imaginary + other.imaginary)
    // def +(other: Double): ComplexNum = new ComplexNum(real + other, imaginary)

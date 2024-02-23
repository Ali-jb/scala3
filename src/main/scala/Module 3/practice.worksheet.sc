
// class Demo {
//     val x = 10
//     val y = x * 2

//     def timesY(a:Int) = a * y   
// }
// val demo = new Demo
// demo.x
// demo.y
// demo.timesY(3)


class Demo:
  val x = 10
  val y = x * 2

  def timesY(a: Int): Int = a * y
  
val demo = new Demo

class TestPublicAndPrivate(val a: String):
  println(s"input variable is $a")
  val b = "##"+a+"##"

val test = TestPublicAndPrivate("steve")

test.a
test.b

// val test_2 = TestPublicAndPrivate()

// write a class with auxiliaty method:
// class Rational(val n: Int, val d: Int):
//   require(d != 0, "Zero denominator!")
  
//   def this(i:Int) = this(i,1)
  
//   override def toString: String = s"R($n/$d)"
  
//   def min(other: Rational): Rational = 
//     if (this.n.toDouble / this.d) < (other.n.toDouble / other.d) then this
//       else other
//   end min

//   def add(other: Rational): Rational = 
//     new Rational (this.n * other.d + this.d * other.n,
//     this.d * other.d)
//   end add

// end Rational

// val half = new Rational(1,2)
// half.n
// half.d

// val fifth = new Rational(1,5)

// val smaller = half.min(fifth)

// // val Rational_test = new Rational(1,0) 


// val sum = half.add(fifth)

// new Rational(1) add new Rational(1,2)



// write a class with factory method:
class Rational private (val n:Int, val d:Int):
  require(d != 0, "ZeroDivision")
  override def toString: String = s"R($n/$d)"
  
  def min(other:Rational):Rational = 
  if (other.n.toDouble / other.d <  this.n.toDouble / this.d) then other 
  else this
  end min
  
  def +(other:Rational):Rational = 
      new Rational (this.n * other.d + this.d * other.n,
    this.d * other.d)
  end + 


end Rational

object Rational:
  
  def apply(n:Int, d:Int):Rational =
    new Rational(n,d) 
  
  implicit def apply(n:Int):Rational = 
    new Rational(n,1)
  
  def apply(fraction:String):Rational =
    val fields = fraction.split("/").map(_.trim.toInt).take(2)
    require(fields.size == 2, "Invalid fraction String")
    new Rational(fields(0), fields(1)) 

end Rational


// new Rational(1,2)
// new Rational(1,2).min(new Rational(1,5))
// new Rational(1,2) + (new Rational(1,5)) because of the private you can't use new! 
// only companion object


val rsample = Rational(3,2)
val rsample_2 = Rational(5)
val rsample_3 = Rational("1/5")
val half = Rational("1/2")
1 + half //it is working because of the "Implicit" before def in companion object 
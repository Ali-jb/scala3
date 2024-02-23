def matchit(x: Any): Unit = x match
    case 10 => println("The number 10")
    case true => println("This is the truth")
    case _ => println("It's something else")

matchit(10)
matchit(2)

def pair(s: String): String = s match
    case "fish" => "chips"
    case "bacon" => "eggs"
    case "tea" => "scones"
    case _ => "not a valid input"

pair("fish")
pair("shotor")

def opposite(s: String): String = s match
    case "white" => "black"
    case "water" => "fire"
    case "happy" => "sad"
    case inword @ ("sane" | "edible" | "secure") => s"in$inword"
    case x => s"not $x"


opposite("white")
opposite("shalgham")
opposite("secure")


val MaxLimit = 10 
val minLimit = 1
def isALimit(x: Int): Boolean = x match
    case MaxLimit => true   //uppercase declares Constant
    case `minLimit` => true //alternatively we can put constants in ` 
    case _ => false         // otherwise match puts value into variable!

isALimit(10)
isALimit(1)
isALimit(20)



def describeNumber(x: Int): String = x match
    case 0                      => "zero"
    case n if n > 0 && n < 100  => "smallish positive"
    case n if n > 100           => "large positive"
    case n if n < 0 && n > -100 => "smallish negative"
    case _                      => "large negative"

describeNumber(-101)
describeNumber(-50)
describeNumber(50)
describeNumber(101)
describeNumber(0)


// def matchOption[Any](o: Option[Any]): String = o match
//     case Some(n: Int) if n >= 10 => "ten or above"
//     case Some(_: Int)            => "less than ten"
//     case None               => "no number"

// matchOption(Some(11))
// matchOption(Some(5))
// matchOption(None)
// matchOption(Some("shotor"))

def matchOption(o: Option[Any]): String = o match {
  case Some(n: Int) if n >= 10 => "ten or above"
  case Some(_) if !o.exists(_.isInstanceOf[Int]) => "no number"
  case _ => "less than ten"
}

matchOption(Some("shotor")) 
matchOption(Some(11))
matchOption(Some(5))
matchOption(None)

val x = 1
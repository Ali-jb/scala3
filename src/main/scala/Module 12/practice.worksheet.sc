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


def matchTuples(t: (Int, Boolean, String)): String = t match
    case(1, flag, string) => s"a 1 followed by $flag and $string"
    case(i, true, "Fred") => s"a true Fred with int $i"
    case(a, b, c)         => s"Some other tuple int $a, flag $b, string $c"

matchTuples(1, false, "Sally")
matchTuples(4, true, "Fred")
matchTuples(2, false, "shalgham")


def matchLists(l: List[Int]): String = l match
    case 1 :: 2 :: rest => s"A 1, 2 list followed by $rest"
    case a :: b :: _      => s"A list with at least 2 elements, starting with $a and $b"
    case a :: Nil         => s"A Single element list of $a"
    case Nil              => "The Empty list"

matchLists(List(1, 2, 3, 4))
matchLists(List(2,4,5,6))
matchLists(List(1))
matchLists(List())


def matchVectors(v: Vector[Int]): String = v match
    case 1 +: 2 +: rest   => s"A 1, 2 vector followed by $rest"
    case Vector(a, b, rest @  _*) => s"A vector with at least 2 elements, starting with $a and $b and the rest is $rest"
    case Vector(a)        => s"A Single element vector of $a"
    case Vector()         => "The Empty vector"

matchVectors(Vector(1, 2, 3, 4))
matchVectors(Vector(2,4,5,6))
matchVectors(Vector(1))
matchVectors(Vector())

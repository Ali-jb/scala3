file://<WORKSPACE>/src/main/scala/Module%2012/practice.worksheet.sc
### java.lang.AssertionError: assertion failed

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.1
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-y39PfhJpSgKoxa-0GcHmnA== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.9/semanticdb-javac-0.9.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.1/scala3-library_3-3.3.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>


action parameters:
uri: file://<WORKSPACE>/src/main/scala/Module%2012/practice.worksheet.sc
text:
```scala
object worksheet{
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
      case Vector(a, b, rest @ _*) => s"A vector with at least 2 elements, starting with $a and $b and the rest is $rest"
      case Vector(a)        => s"A Single element vector of $a"
      case Vector()         => "The Empty vector"
  
  matchVectors(Vector(1, 2, 3, 4))
  matchVectors(Vector(2,4,5,6))
  matchVectors(Vector(1))
  matchVectors(Vector())
  
  
  try
      4 / 0
  catch 
      case _: ArithmeticException => 0
  
  import scala.util.* 
  
  Try(4/2)
  Try(4/0)
  
  def matchTry(t: Try[_]): String = t match
      case Success(x) => s"it worked! result is $x"
      case Failure(x) => s"it failed with $x"
  
  
   matchTry(Try(4/2))
   matchTry(Try(4/0))
  
  case class Address(street: String, city: String, postCode: Option[String])
  case class Person(name: String, phone: Option[String], address: Option[Address])
  
  val harry = Person(name = "Harry", phone = Some("111"), address = Some(Address(street = "folan. St", city = "Godric's Hollow", postCode = Some("321-222-333"))))
  
  def postCodeForHarry(person: Person) = person match
      case Person("Harry", _, Some(Address(street, city, Some(postcode)))) => 
          println("Harry found with postcode")
          println(s"City $city")
          println(s"Street $street")
          
      case _ => ""
  
  postCodeForHarry(harry)
  postCodeForHarry(Person("sally", None, None))
  postCodeForHarry(Person("Harry", None, Some(Address("random. St", "someUnknownCity", Some("321-222-333")))))
  postCodeForHarry(Person("Harry", None, Some(Address(street = "folan. St", city = "Godric's Hollow", postCode = None))))
  
  
  def describeType(x: Any) = x match
      case i: Int if i > 0 => s"int ${i * i}"
      case d: Double => s"Double $d"
      case s: String => s"String ${s.reverse} is reversed of ${s}"
      case _ => "Some other type"
  
  describeType(4)
  describeType(4.3)
  describeType("Hello")
  describeType(List(1,2,3))
   
  
  //alternatively:
  val s: Any = "Hello"
  val b = if (s.isInstanceOf[String]) then s"String ${s.toString.reverse} is reversed of ${s}" else None
  
  //type erasure
  
  val m1: Map[Int, String] = Map(1 -> "one", 2 -> "two")
  
  def withIntStringMap(x: Any): Int = x match
      case m: Map[_,_] => // map[String, Int]
          m.head._1 match
              case i: Int => i * i 
              case _ => 0
      case _ => 0
  
  
  withIntStringMap(Map(1 -> "One"))
  withIntStringMap(List(1, "One"))
  withIntStringMap(Map("One" -> 1))
  
  //val is a pattern match!
  val map = Map("a" -> 1, "b" -> 2)
  val (key, value) = map.head
  
  case class Person1(name: String, age: Int)
  val person = Person1("John", 30)
  val Person1(name, age) = person
  
  // pattern matching in for loops
  val sally = Person(name = "sally", phone = None, address = Some(Address(street = "folan. St", city = "Godric's Hollow", postCode = Some("321-222-333"))))
  
  val people = List(sally, harry)
  harry
  for 
      case Person(name, Some(phone), _) <- people
  yield name -> phone
  
  //sealed traits, enums, unapply and custom extractors
  
  //custom extractor example
  
  val coordStr = "-120.123, 33.007"
  
  object Coords:
      def unapply(coordStr: String): Option[(Double, Double)] = 
          Try {
              val fields = coordStr.split(",").map(_.trim.toDouble)
              (fields(0), fields(1))
               }.toOption
  
  
  Coords.unapply(coordStr)
  
  coordStr match
      case Coords(x, y) =>
          println(s"x = $x")
          println(s"y = $y")
      
      case _ => None
  
  // match regex 
  val coords2 = "123,45678"
  val regexDemo = "(\\d+),(\\d+)".r
  
  coords2 match
      case regexDemo(lat, long) => (lat, long)
  
  
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:11)
	dotty.tools.dotc.core.TypeOps$.dominators$1(TypeOps.scala:248)
	dotty.tools.dotc.core.TypeOps$.approximateOr$1(TypeOps.scala:382)
	dotty.tools.dotc.core.TypeOps$.orDominator(TypeOps.scala:395)
	dotty.tools.dotc.core.Types$OrType.join(Types.scala:3435)
	dotty.tools.dotc.core.Types$OrType.widenUnionWithoutNull(Types.scala:3451)
	dotty.tools.dotc.core.Types$Type.widenUnion(Types.scala:1296)
	dotty.tools.dotc.core.ConstraintHandling.widenOr$1(ConstraintHandling.scala:652)
	dotty.tools.dotc.core.ConstraintHandling.widenInferred(ConstraintHandling.scala:668)
	dotty.tools.dotc.core.ConstraintHandling.widenInferred$(ConstraintHandling.scala:29)
	dotty.tools.dotc.core.TypeComparer.widenInferred(TypeComparer.scala:30)
	dotty.tools.dotc.core.TypeComparer$.widenInferred(TypeComparer.scala:3031)
	dotty.tools.dotc.typer.Namer.rhsType$1(Namer.scala:1926)
	dotty.tools.dotc.typer.Namer.cookedRhsType$1(Namer.scala:1932)
	dotty.tools.dotc.typer.Namer.lhsType$1(Namer.scala:1933)
	dotty.tools.dotc.typer.Namer.inferredResultType(Namer.scala:1944)
	dotty.tools.dotc.typer.Namer.inferredType$1(Namer.scala:1691)
	dotty.tools.dotc.typer.Namer.valOrDefDefSig(Namer.scala:1698)
	dotty.tools.dotc.typer.Namer.defDefSig(Namer.scala:1789)
	dotty.tools.dotc.typer.Namer$Completer.typeSig(Namer.scala:791)
	dotty.tools.dotc.typer.Namer$Completer.completeInCreationContext(Namer.scala:934)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:814)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.ensureCompleted(SymDenotations.scala:393)
	dotty.tools.dotc.typer.Typer.retrieveSym(Typer.scala:2989)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3014)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:2669)
	dotty.tools.dotc.typer.Typer.typedTypeOrClassDef$1(Typer.scala:3036)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3040)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:2812)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3081)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:44)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$adapted$1(TyperPhase.scala:54)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:440)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:54)
	dotty.tools.dotc.typer.TyperPhase.runOn$$anonfun$3(TyperPhase.scala:88)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:88)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:246)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1321)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:262)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:270)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:279)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:279)
	dotty.tools.dotc.Run.compileSources(Run.scala:194)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:44)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:109)
```
#### Short summary: 

java.lang.AssertionError: assertion failed
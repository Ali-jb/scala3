// class Module02 extends KoanSuite with Matchers with SeveredStackTraces {

//   test ("Parameterize arrays with type") {
    val greetStrings = new Array[String](3)
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "World"

    // what happens if you replace the above line with:
    // greetStrings(2) = 2

    // join the strings together
    val concat = greetStrings(0) + greetStrings(1) + greetStrings(2)

    // and what should this equal?
    // concat should be ("Hello, World")
    println(concat)
//   }

//   test ("Array creation") {
    // Calling Array() directly invokes the apply method - the following two statements are equivalent
    val numNames = Array("zero", "one", "two")
    val numNames2 = Array.apply("zero", "one", "two")

    numNames.length 
    numNames2.length
   numNames == numNames2
   numNames sameElements numNames2
   numNames.sameElements(numNames2) || numNames.eq(numNames2)
   numNames.sameElements(numNames2) && numNames.eq(numNames2)

    // (numNames == numNames2) should be (false)

    // (numNames sameElements numNames2) should be (true)
//   }

//   test ("List immutability") {
    // val oneTwo = List(1,2)
//     val threeFour = List(3,4)
//     // 
//     val oneTwoThreeFour = oneTwo ::: threeFour
//     println(oneTwo)
//     println(oneTwo)
//     println(oneTwoThreeFour) 
// //   }

// //   test ("List cons") {
//     val twoThree = List(2,3)
//     val newList = 1 :: twoThree
//     println(newList)
//     // List(1,List(2,3))
    
//     val newList2 = twoThree.::(1)
 
//   }

//   test ("Create a list and convert to Array") {
    // fill in the method below to concatenate the two lists and then convert them to an array
    // so that it satisfies the tests below
    //

    // def concatListsToArray(l1 : List[Int], l2 : List[Int]) : Array[Int] = {(l1 ::: l2).toArray}

    def concatListsToArray(l1 : List[Int], l2 : List[Int]) : Array[Int] = (l1 ::: l2).toArray
// import scala.reflect.ClassTag

// def concatArraysToArray[A: ClassTag](a: Array[A], b: Array[A]): Array[A] = {
//   a ++ b
// }
// val res = concatArraysToArray(Array("shotor", 2, 3), Array(9,8,8))
// println(res.mkString(", "))


//     val oneTwo = List(1,2)
//     val threeFour = List(3,4)

//     concatListsToArray(oneTwo, threeFour) should equal (Array(1,2,3,4))
//   }

//   test ("Take two arrays, and concatenate them in a list") {
//     def concatArraysToList(a1 : Array[Int], a2 : Array[Int]) : List[Int] = {
//       a1.toList ::: a2.toList
//     }

// def concatArraystoList(a1 : Array[Int], a2 : Array[Int]) = {
//   a1.toList ::: a2.toList
// }


//     val oneTwo = Array(1,2)
//     val threeFour = Array(3,4)

//     concatArraystoList(oneTwo, threeFour)   

//   test ("Exploring Tuples") {
    // val t = (0, 'u', 8, 1, "too")

//     t._1 should be (0)
//     t._2 should be ('u')
//     t._3 should be (8)
//     t._4 should be (1)
//     t._5 should be ("too")

//     // Arity is the number of arguments
    // t.productArity

//     // and you can iterate over the arguments too
    // val test = t.productIterator
    // test.next()
    // test.next()
//   }

  // test ("Map a tuple to strings") {
// val t = (0, 'u', 8, 1, "too")

//     // //using a var array
//     val iterator = t.productIterator
//     var array = new Array[String](0)
//     while(iterator.hasNext) {
//       val item = iterator.next().toString
//       println(item)
//       array = array :+ item
//     }
//     val l = array.toList

//     //using a val array
//     val array1 = new Array[String](t.productArity)
//     var counter = 0
//     while(iterator.hasNext) {
//       val item = iterator.next().toString
//       println(item)
//       array(counter) = item
//       counter = counter + 1
//     }
//     val l2 = array.toList
    // l should be (List("0", "u", "8", "1", "too"))
  // }

//   test ("Immutable set in var") {
    var getSet = Set("Ready", "Steady")

    getSet = getSet.+("Go!")

    // Add a line below to satisfy the test
    getSet
    
    // What happens if you make the var a val above? Why?
//   }

//   test ("Mutable set in a val") {
//     var getSet = scala.collection.mutable.Set("Ready", "Steady")

//     getSet = getSet.+("Go!")

//     getSet should be (Set("Ready", "Steady", "Go!"))

//     val getSet1 = scala.collection.mutable.Set("Ready", "Steady")

//     getSet1.+("Go!")

//     // what happens if you make the var a val above? Why? Is this a good idea?
//   }

//   test ("Immutable map in a var") {
    // var mutMap = Map[Int, String]()

    // mutMap += (1 -> "Uno")
    // mutMap += (2 -> "Dos")
    // mutMap += (3 -> "Tres")

    // mutMap(2) should be ("Dos")

    // mutMap += (2 -> "Two")

    // mutMap(2) should be ("Two")

    // What happens if you uncomment the line below? Why?
    // mutMap += (2 -> 2)
    // mutMap(2) should be ("Two")
//   }

//   test ("Mutable map in a val") {
//     val mutMap = scala.collection.mutable.Map[Int, String]()

//     mutMap += (1 -> "Uno")
//     mutMap += (2 -> "Dos")
//     mutMap += (3 -> "Tres")

//     mutMap(2) should be ("Dos")

//     mutMap(2) = "Two"

//     mutMap(2) should be ("Two")

//     mutMap += (2 -> "Deux")

//     mutMap(2) should be ("Deux")

//     // What happens if you uncomment the line below? Why?
//     // mutMap += (2 -> 2)
//     mutMap(2) should be ("Deux")
//   }
// }
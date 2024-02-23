// val t = ("u", 9, 0, "too")
// val iterator = t.productIterator
// var resArray = Array[String]()
// while (iterator.hasNext) {
//     resArray  = resArray :+ iterator.next().toString()
// }
// println(resArray.toList)

// val t = ("u", 9, 0, "too")
// val iterator = t.productIterator
// val resArray = new Array[String](t.productArity)
// var counter = 0
// while (iterator.hasNext) {
// resArray(counter) = iterator.next().toString
// counter += 1
// }
// println(resArray.toList)
 



var mutMap = Map[Int, String]()

mutMap += (1 -> "Uno")
mutMap += (2 -> "Dos")
mutMap += (3 -> "Tres")

mutMap(2) 

mutMap += (2 -> "Two")

mutMap(2)



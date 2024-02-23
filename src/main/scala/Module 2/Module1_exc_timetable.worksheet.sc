var x = 2
while (x<=5) {
    println(x)
    var y = 1
    while (y<=5) {
        var result = (x * y).toString
        if (result.contains("4") || result.contains("6")) {
        println(s"$x times $y is ${result}")
        }
        y+=1
    }
    x+=1
}



if (2>1) {
    println("yes")
}

if 2>1
then print("yes")

val test = 1 + 2
val test_2 = 1.-(2)
val test_3 : String = "Shalgham"
test_3.charAt(2)
//test_3 chatAt 1
System.out println "Hello"


val list_1: List[String] = List("shotor", "gav", "palang")
val array_1: Array[Int] = Array(1,2,3)
array_1(1)
array_1.apply(1)
list_1(0)

array_1.update(0,4)
array_1.toList

array_1(0) = 4
array_1.toList

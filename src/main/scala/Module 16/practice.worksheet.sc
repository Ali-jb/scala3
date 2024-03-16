import scala.util.Failure
import scala.util.Success
import scala.concurrent.* 
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

val futureInt = Future (1 + 1)
// val futureInt = Future { Thread.sleep(1000L);1 + 1 }

Future.successful(4)
Future.failed(new RuntimeException("failure"))

futureInt.isCompleted
futureInt.value

// ------------------------------------------------

val fa = Future(1)
val fb = Future { Thread.sleep(1000); 2}
val fc = Future(3)
val fd = Future { Thread.sleep(500); "the answer is"}

val fRes = for 
    a <- fa
    b <- fb
    c <- fc
    s <- fd 
yield
    val sum = a + b + c
    s"$s $sum"

fRes.isCompleted
fRes.value

Thread.sleep(2000)
fRes.isCompleted
fRes.value

Await.ready(fRes, 1.second)
Await.result(fRes, 1.second)

// ------------------------------------------------

val f1: Future[Any] = Future(10)
val f2 = f1.collect{
    case i: Int => i
}
f2.filter(_>=11)
f2.filter(_<=11)

f2.transform(i => i * 5, {ex =>
    println(ex.getMessage)
    throw new RuntimeException("it failed to filter", ex)
})

// ------------------------------------------------

 val f3 = f2.andThen {
    case Success(i) if i % 2 == 0 => println("it's even") 
 }
 Await.result(f3, 1.second)

f3.onComplete{
    case Success(i) => println("success")
    case Failure(ex) => println("failure")
 }

f3.foreach(i => println(s"result: $i"))
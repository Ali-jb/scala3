import scala.concurrent.* 
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

val futureInt = Future (1 + 1)
// val futureInt = Future { Thread.sleep(1000L);1 + 1 }

Future.successful(4)
Future.failed(new RuntimeException("failure"))

futureInt.isCompleted
futureInt.value

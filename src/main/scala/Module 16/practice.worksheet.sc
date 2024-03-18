import scala.util.control.NonFatal
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

// ------------------------------------------------
//recover from futures
val failedFuture = Future.failed(new RuntimeException("failed future test"))
failedFuture.fallbackTo(Future(0))

val fr =failedFuture.recover {
    case _: RuntimeException => 0 
}

val fr2 =failedFuture.recoverWith {
    case _: RuntimeException => Future(0) 
}

// ------------------------------------------------

val nums = List(1, 2, 3, 4, 5)
def square(i: Int): Future[Int] = Future(i * i)

val futs: List[Future[Int]] = nums.map(square)
val futList = Future.sequence(futs)
Await.ready(futList, 1.second)

// alternatively
val futList2 = Future.traverse(nums)(square)
Await.ready(futList2, 1.second)

// ------------------------------------------------

val ft1 = Future { Thread.sleep(10); 10 }
val ft2 = Future { Thread.sleep(5); 5 }
val ft3 = Future { Thread.sleep(20); 20 }
val sft = List(ft1, ft2, ft3)

Await.ready(Future.firstCompletedOf((sft)), 1.second)
Await.ready(Future.foldLeft(sft)(0)(_ + _), 1.second)
Await.ready(Future.reduceLeft(sft)(_ + _), 1.second)

// ------------------------------------------------

val promise = Promise[Int]
val future = promise.future

future.isCompleted
future.value

promise.success(10)

future.isCompleted
future.value

// ------------------------------------------------

//future batching
def calc(i: Int): Future[Int] = Future {
    println(s"calculating for $i")
    Thread.sleep(500)
    i * i 
}

def processSeq(xs: Vector[Int]): Future[Vector[Int]] = 
    val allFutures: Vector[Future[Int]] = xs.map(calc)
    Future.sequence(allFutures)

def processSeqBatch(xs: Vector[Int], batchSize: Int): Future[Vector[Int]] = 
    val batches = xs.grouped(batchSize)
    val start = Future.successful(Vector.empty[Int])

    batches.foldLeft(start) {(accF, batch) =>
        for
            acc <- accF
            batchRes <- processSeq(batch)
        yield acc ++ batchRes
    }

val numbersVec = (1 to 20).toVector



val result2 = processSeqBatch(numbersVec, 2)
Await.result(result2, 20.second)

val result1 = processSeq(numbersVec)
Await.result(result1, 20.seconds)

// ------------------------------------------------
//future retrying

import scala.util.Try

var time = 0
def resetTries(): Unit = time = 0

def calc(): Int = 
    if time > 3 then 10 else
        time += 1
        throw new IllegalStateException("not yet!")

Try(calc())
Try(calc())
Try(calc()) 
Try(calc())
Try(calc())
resetTries()
Try(calc())


def fCalc(): Future[Int] = Future(calc())
val future2 = fCalc(). 
recoverWith{ case NonFatal(_) => fCalc() }.
recoverWith{ case NonFatal(_) => fCalc() }.
recoverWith{ case NonFatal(_) => fCalc() }.
recoverWith{ case NonFatal(_) => fCalc() }

Await.ready(future2, 10.seconds)

//best practice
def retry[T](op: => T, retries: Int): Future[T] = 
    Future(op) recoverWith {
        case NonFatal(_) if retries > 0 => retry(op, retries - 1)
    }
resetTries()
val future3 = retry(calc(), 3)
Await.ready(future3, 10.seconds)

resetTries()
val future4 = retry(calc(), 5)
Await.ready(future4, 10.seconds)

// ------------------------------------------------
// retrying with back-offs (a cooldown between retries)
import java.util.{Timer, TimerTask}
val timer = new Timer("retrying", true)

def after[T](duration: FiniteDuration)(op: () => Future[T])(
    implicit ec: ExecutionContext): Future[T] =

    val promise = Promise[Future[T]]
    val futureHandle = promise.future
    val task = new TimerTask {
        override def run(): Unit = {
            ec.execute(() => promise.success(op()))
        }
    }

    timer.schedule(task, duration.toMillis)
    futureHandle.flatten

def retryBackoff[T](op: _ => T, backoffs: Seq[FiniteDuration]): Future[T] = 
    Future(op) recoverWith{
        case NonFatal(_) if backoffs.nonEmpty => 
            after(backoffs.head)(() => retryBackoff(op, backoffs.tail))
    }


resetTries()

val future5 = retryBackoff(calc(), Seq(500.millis, 500.millis, 1.second, 1.second, 2.seconds))
Await.result(future5, 2.minutes)
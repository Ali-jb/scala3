import scala.collection.mutable
import scala.collection.immutable

def popImmutableQueue(q: immutable.Queue[Int]): (Int, immutable.Queue[Int]) = 
    q.dequeue


def popMutableQueue(q: mutable.Queue[Int]): Int = 
    q.dequeue()

val imQ = immutable.Queue(1,2,3,4)
val mQ = mutable.Queue(1,2,3,4)

popImmutableQueue(imQ)
popMutableQueue(mQ)

// ____________________________________________________________________________

val q = immutable.Queue(1,2,3,4)
val eq = immutable.Queue.empty[Int]

val xs = List(1,2,3,4)
val exs = List.empty[Int]

val v = Vector(1,2,3,4)
val ev = Vector.empty[Int]

val s = Set(1,2,3,4)
val es = Set.empty[Int]

val arr = Array(1,2,3,4l)

q == xs
q == v
xs == v

// xs == s
// xs == arr
xs == arr.toIndexedSeq
xs == arr.toList


xs.toVector
xs.toArray
xs.toSet

// xs.toMap
xs.zipWithIndex.toMap

// xs.toQueue
xs.to(immutable.Queue)

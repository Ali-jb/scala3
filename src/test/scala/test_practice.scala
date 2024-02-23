import org.scalatest.matchers.should.Matchers
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.funspec.AnyFunSpec

class TestingSuiteDemo extends AnyFunSuite with Matchers:
    val nums: List[Int] = (1 to 20).toList

    test("Filtering a list")  {
        val filtered = nums.filter(_ > 15)
        assert(filtered === Seq(16,17,18,19,20))
    }

    test("summing a list") {
        nums.sum should be (210)
    }

    test("something else")(pending)


//matchers
class TestingSpecDemo extends AnyFunSpec with Matchers {
    describe ("various Matchers") {
        describe("on a list of numbers") {
            val nums = (1 to 20).toList
            val threeMults = nums.filter(_ % 3 == 0)
            
            they("should allow a wide and varied language for matching") {
            val x = 10 * 2
            
            x should be (20)

            threeMults should have size (6)
            threeMults should contain allOf (3, 6, 12, 15)
            threeMults should not contain (10)
            threeMults should be (Vector(3, 6, 9, 12, 15, 18))
            threeMults should be (sorted)
            all(threeMults) should be > (0)
            atLeast(3, threeMults) should be > (10)


            
        }
        }
    }


    describe("on a case class example") {
        they("should allow easy field checking") {
            case class Person(first: String, last: String, age: Int)
            val p1 = Person("Harry", "Potter", 34)

            import p1.*
            first should be ("Harry")
            last should be ("Potter")
            age should be (34)
        }
    }
}


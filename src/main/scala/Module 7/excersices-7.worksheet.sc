import Engine.shunt
import Engine.steam
import scala.collection.mutable.ListBuffer
import scala.collection._

// _______________________________________________________________

abstract class RollingStock:
    val name: String

abstract class Car extends  RollingStock:

    val carries: String
    def pulled: String = s"$name Carrying $carries"

    override def toString: String = pulled


abstract class Engine extends RollingStock:

    val cars: ListBuffer[Car] = ListBuffer.empty[Car]

    def pull: String = s"$name pulls ${cars.mkString("and ")} \n}"

    val maxCars: Int

    def add(car: Car): this.type =
      if (cars.length >= maxCars)
        throw new IllegalStateException("oy! pore haji!")
      cars += car
        this
    override def toString: String = pull

// _______________________________________________________________


class PassengerCar extends Car:
    val name: String = "Passenger Car"
    val carries: String = "People"

class CargoCar extends Car:
    val name: String = "Cargo Car"
    val carries: String = "Cargo"


// _______________________________________________________________

class SteamEngine extends Engine:
    val name: String = "Steam Engine"
    val maxCars: Int = 3

class DieselEngine extends Engine:
    val name: String = "Diesel Engine"
    val maxCars: Int = 6

// _______________________________________________________________

// val steamEngine = new SteamEngine
// steamEngine.add(new PassengerCar)
// steamEngine.add(new PassengerCar)

// steamEngine.pull

// _______________________________________________________________

// val steamEngine = new SteamEngine
// steamEngine.add(new PassengerCar)

// steamEngine.add(new CargoCar)
// steamEngine.add(new PassengerCar)

// steamEngine.add(new CargoCar)

// _______________________________________________________________

// val dieselEngine = new DieselEngine
// dieselEngine.add(new CargoCar)
// dieselEngine.add(new PassengerCar)
// dieselEngine.add(new CargoCar)
// dieselEngine.add(new PassengerCar)
// dieselEngine.add(new CargoCar)

// dieselEngine.pull

class ShuntEngine extends Engine:
    val name: String = "Shunt Engine"
    override def pull: String =  name + " doesn't pull, it pushes " + cars.mkString(" and ")
    val maxCars: Int = 10

// val shuntEngine = new ShuntEngine
// shuntEngine.add(new CargoCar)
// shuntEngine.add(new PassengerCar)
// shuntEngine.add(new CargoCar)
// shuntEngine.add(new PassengerCar)
// shuntEngine.add(new CargoCar)
// shuntEngine.add(new PassengerCar)
// shuntEngine.add(new CargoCar)
// shuntEngine.add(new PassengerCar)
// shuntEngine.pull

// _______________________________________________________________

object Engine:
    def diesel = new DieselEngine
    def steam = new SteamEngine
    def shunt = new ShuntEngine
  
object Car:
    def passenger = new PassengerCar
    def cargo = new CargoCar

// val steamEngine = Engine.steam
// steamEngine add Car.passenger    // crafty use of infix operator syntax to make it read nicely
// steamEngine add Car.passenger
// steamEngine.pull

// _______________________________________________________________


// extra credit - alter the add method in the Engine abstract class to return the engine instance
// at the end of the method, so that the cars can be added in a chain like this:##add return type this.type and one at the end of method

val e1 =  SteamEngine()
Engine.steam.add(Car.cargo).add(Car.passenger).add(Car.cargo)

// val steamTrain = Engine.steam add Car.cargo add Car.passenger add Car.cargo
//
// and write a test to make sure that it works, as expected, for all trains and carriages
//
// Why didn't that change break your existing tests?
// You have effectively created a simple DSL for creating trains, how do you like that?

// test ("Chained train creation") {
// val steamEngine = Engine.steam add Car.cargo add Car.passenger add Car.passenger

// steamEngine.pull should be ("Steam engine pulls Cargo car carrying cargo and Passenger car carrying people and Passenger car carrying people")
// _______________________________________________________________


// extra extra credit: there may be some repetition in your ShuntEngine implementation where it lists
  // out the cars in the overridden pull method. Refactor out the car string resolution into a separate
  // method and use that in both the Engine pull method and the overridden ShuntEngine pull method to
  // reduce the duplication. Make sure all of the tests still pass.
  //
  // If you already did this without being prompted, you get a gold star for being a smart-ass :-)


// ## i don't get it :-/


  val e2 = ShuntEngine()
  e2.add(Car.passenger).add(Car.passenger)
  
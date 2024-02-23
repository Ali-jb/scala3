// Vehicle
// Car
// VehicleStorage
// ParkingStructure
// _______________________________________________________________
abstract class Vehicle:
    def name: String
    def description: Vector[String]
    override def toString: String = s"Vehicle($name)"

    def fullDescription: String = 
        (name +: description).mkString("\n")

// _______________________________________________________________

case class Car(
    name: String,
    description: Vector[String] = Vector.empty

) extends Vehicle
// _______________________________________________________________

abstract class VehicleStorage:
    def name: String
    def vehicles: Vector[Vehicle]

    def vehicleCount: Int = vehicles.size

    override def toString: String = s"$name with $vehicleCount vehicles"

// _______________________________________________________________

 case class ParkingStructure(
name: String,
vehicles: Vector[Vehicle]
 ) extends VehicleStorage:
    def describeGarage: String =
        val vehicleString = vehicles.mkString(", ")
         s"$name contatining $vehicleString"
    
    override def toString: String = describeGarage

// // _______________________________________________________________

val mustang = Car("ford mustang", Vector("1965 mustang", "metallic blue", "302 ci v8"))

val datsun = Car("datsun 280z", Vector("1982 datsun 280z", "candy apple red", "2.8 liter I6"))

mustang.fullDescription

val lot = ParkingStructure("parking garage", Vector(mustang, datsun))
lot.vehicleCount
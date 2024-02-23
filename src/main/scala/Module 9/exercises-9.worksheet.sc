import java.time.LocalDateTime
// Below is a fake database stateful entity called StatefulEntity that has two methods
// on it, save and cancel that just print up what they do, (e.g. print a string Save or
// Cancel) and always return true. It's not hard to stretch your imagination to where these
// really use a database session to commit or abort, etc.
// There is also a companion object which holds on to a map of IDs to stateful entities that
// simulates the actual database. The save operation updates the entry for that ID in the
// "database" and you can retrieve an object by its UUID.
//
// You could even think of this as a simple database mock object
// just a test comment

import scala.collection.mutable
import java.util.UUID


object StatefulEntity:
  val storedEntities = new mutable.HashMap[UUID, StatefulEntity]

  def store(entity: StatefulEntity): Unit = storedEntities(entity.id) = entity

  def findById(id: UUID):
      Option[StatefulEntity] = storedEntities.get(id)



class StatefulEntity:
  val id = UUID.randomUUID

  def save() = 
    println("Save")
    StatefulEntity.store(this)
    true


  def cancel() = 
    println("Cancel")
    true

// def showRecords(): Unit = 
//   for ((id, entity) <- StatefulEntity.storedEntities) 
//     println(s"ID: $id, Entity: $entity")


val se1 = new StatefulEntity
val se2 = new StatefulEntity

se1.id 
se1.id 

StatefulEntity.findById(se1.id)
StatefulEntity.findById(se2.id)

se1.save()
se2.save()

se1.cancel()
se2.cancel()

// Add a trait called CreatedUpdated which extends StatefulEntity but adds two new Option[LocalDateTime]
// date fields, one for the created date, and the other for the updated date
// (just use java.time.LocalDateTime for now). The behavior  as follows:
// Override the save() method so that the createdDate is set to now if it is not already set, or
// left alone if it is set. The save method should update the updatedDate field any time save is called
// whether it is set or not. The save method should still call the save() on StatefulEntity when it
// has done it's work
// cancel() does not need to update either date, hence doesn't need to be overridden
// Also, the two date fields  private, but exposed through a couple of accessor methods called
// whenCreated and lastUpdated.
//
// To get the current datetime as a LocalDateTime, just use LocalDateTime.now()

trait CreatedUpdated extends  StatefulEntity:
  private var createdDate: Option[LocalDateTime] = None
  private var updatedDate: Option[LocalDateTime] = None

  override def save() = 
    val saved = super.save()
    if saved then
      val newDate = LocalDateTime.now()

      if createdDate.isEmpty then createdDate = Some(newDate)
      updatedDate = Some(newDate)
    saved

  def whenCreated = createdDate
  def lastUpdated = updatedDate

val se = new StatefulEntity with CreatedUpdated

se.whenCreated
se.lastUpdated 

se.cancel()

se.whenCreated  
se.lastUpdated  

se.save()

se.whenCreated 
se.lastUpdated 
val created = se.whenCreated

Thread.sleep(1001) // yuk - but it's easy
se.save()
se.whenCreated 
se.lastUpdated 

val updated = se.lastUpdated

Thread.sleep(1001)
se.cancel()
se.whenCreated 
se.lastUpdated 

// Add another trait, this time it should be called CreateOnly, and should override the save operation
// to only save if the object has never been saved before - if the object has already been saved it
// should not save the object, and should return false instead of true.
//
// Uncomment the tests to make sure it works


trait CreateOnly extends StatefulEntity: 
  var alreadySaved = false
  
  override def save(): Boolean = 
  if alreadySaved then false
  else
      alreadySaved = true
      super.save()
  

val se_new = new StatefulEntity with CreateOnly with CreatedUpdated

se_new.whenCreated 
se_new.lastUpdated 

se_new.save() 

se_new.whenCreated
se_new.lastUpdated

val created_new = se_new.whenCreated

Thread.sleep(1001)

se_new.save() 


se_new.whenCreated
se_new.lastUpdated



// Finally, let's create a new class for convenience, called StatefulEntityWithDateCreateOnly that
// is based on the StatefulEntity and adds the two traits, so that they don't need to be composed
// each time - the following tests should pass.


class StatefulEntityWithDateCreateOnly extends StatefulEntity with CreatedUpdated with CreateOnly

val se_final = new StatefulEntityWithDateCreateOnly



se_final.whenCreated
se_final.lastUpdated

se_final.save()

se_final.whenCreated 
se_final.lastUpdated 
val created_final = se_final.whenCreated

Thread.sleep(1001)

se_final.save() 
se_final.whenCreated 
se_final.lastUpdated 
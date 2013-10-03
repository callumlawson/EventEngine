package Core

import scala.concurrent.duration._
import Core.PersonalityTag.{WellSpoken, Meddler, Liar}
import Core.Person.{Bobby, Joe}
import scala.collection.mutable.Set

/**
 * Created with IntelliJ IDEA.
 * User: Callum
 * Date: 26/09/13
 * Time: 21:33
 */
//This is an evil prototype no nice code here!
object EventEngine {
  def main(args: Array[String]) {
    new Main()
  }
}

class Main {
  var events: List[Event[_]] = List()
  var universe: Tag = _

  println("Well bugga: " + WellSpoken().isInstanceOf[World])

  setupWorld()
  setupEvents()
  resolveEvents()
  displayWorld()

  Timer.every(1000 millisecond) {}

  def setupWorld() {
    universe =
      World(Set(
        Joe(Set(Meddler(), VeryHealthy())),
        Bobby(Set(Liar(), RedMist()))
      ))
    println("Initial world: " + universe)
  }

  def setupEvents() {
    addEvent(Event[World]("Make The World Well Spoken", List(TagOps.addTag(WellSpoken()))))
    addEvent(Event[World]("Make The World Well Spoken", List(TagOps.addTag(WellSpoken()))))
//    Event("If Liar Add Meddler", List(tag => println("Yollo!")))
  }

  def addEvent(event: Event[_]) {
    events = event :: events
  }

  def resolveEvents() {
    events.foreach(applyEventToTagAndChildren(universe))
  }

  def displayWorld() {
    println("Final world: " + universe)
  }

  def applyEventToTagAndChildren(parent: Tag)(event: Event[_]) = {
    def applyToAllTags(tag: Tag) {
      event(tag);
      tag.tags.foreach(applyToAllTags)
    }
    applyToAllTags(parent)
  }
}
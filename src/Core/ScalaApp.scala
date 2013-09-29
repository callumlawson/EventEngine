package Core

import scala.concurrent.duration._
import Core.HealthTag.{VeryHealthy, RedMist}
import Core.PersonalityTag.{WellSpoken, Meddler, Liar}
import Core.Person.{Bobby, Joe}

/**
 * Created with IntelliJ IDEA.
 * User: Callum
 * Date: 26/09/13
 * Time: 21:33
 */
//This is an evil prototype no nice code here!
object ScalaApp {
  def main(args: Array[String]) {
    new Main()
  }
}

class Main {

  var events: List[Event] = List()

  Timer.every(1000 millisecond) {
//    println(World(List()).getClass())
  }

  def MakeSomeTags() {
    RedMist()
    VeryLoyal(BlackKnives())
    ReadyToBackstab(Clowns())
    Liar()
    new Tag(List(Meddler()))

    SetupWorld()
  }

  def SetupEvents() {
    var killJoe: Event = Event(World(List()), List(TagOps.AddTag(WellSpoken())))
    events = killJoe :: events
  }

  def SetupWorld() {
    var universe: Tag =
      World(List(
        Joe(List(Meddler(), VeryHealthy())),
        Bobby(List(Liar(), RedMist()))
      ))
  }
}
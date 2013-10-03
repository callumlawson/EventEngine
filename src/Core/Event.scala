package Core
import scala.reflect._
/**
 * User: Callum
 * Date: 28/09/13
 * Time: 20:52
 */
case class Event[Context](eventName: String, operations: List[Tag => Unit], predicates: List[Tag => Boolean] = List((tag: Tag) => true)) {

  def apply(tag: Tag){

    if (!tag.getClass().isInstanceOf[Context]) {
      return
    }

    if (predicates.forall(predicate => predicate(tag))) {
      println("")
      println("Event: " + eventName + "'s predicates have been satisfied, applying operation(s): ")
      operations.foreach(operation => operation(tag))
      println("")
    }
  }
}

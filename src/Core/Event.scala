package Core

/**
 * User: Callum
 * Date: 28/09/13
 * Time: 20:52
 */
case class Event(context: Tag, operations: List[Tag => Unit], predicates: List[Tag => Boolean] = List((tag: Tag) => true)) {
  def apply(tag: Tag) {
    if (context.getClass() != tag.getClass()) {
      return
    }
    if (predicates.forall(predicate => predicate(tag))) {
      operations.foreach(operation => operation(tag))
    }
  }
}

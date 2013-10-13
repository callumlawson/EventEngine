package Core

/**
 * User: Callum
 * Date: 06/10/13
 * Time: 19:29
 */
class Tag(name: String, children: List[Tag]) extends TagClassifier(name, children){ }

object Tag {
  def apply(name: String, children: List[Tag] = List()) = new Tag(name, children)
}

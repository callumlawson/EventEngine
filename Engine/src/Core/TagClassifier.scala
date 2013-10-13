package Core

/**
 * User: Callum
 * Date: 06/10/13
 * Time: 19:29
 */
class TagClassifier(name: String, children: List[TagClassifier]) {}

object TagClassifier {
  def apply(name: String, children: List[TagClassifier] = List()) = new TagClassifier(name, children)
}

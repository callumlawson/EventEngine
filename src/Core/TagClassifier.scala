package Core

/**
 * User: Callum
 * Date: 06/10/13
 * Time: 19:29
 */
class TagClassifier(label: String, children: List[Tag]) extends Tag(label){

}

object TagClassifier {
  def apply(label: String, children: List[Tag]) = new TagClassifier(label, children)
}

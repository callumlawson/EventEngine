package OldCore

import scala.collection.mutable.Set
/**
 * User: Callum
 * Date: 28/09/13
 * Time: 15:06
 */
class Tag(val tags: Set[Tag]) {
  def addTag(tag: Tag){
    tags += tag
  }
  def removeTag(tag: Tag) {
    tags -= tag
  }
}

class TaglessTag extends Tag(Set()){}

trait OrderedTagCollection extends Tag{
  def ordering: Seq[Tag]
}
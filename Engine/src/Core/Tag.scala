package Core

/**
 * User: Callum
 * Date: 06/10/13
 * Time: 19:29
 */
class Tag(name: String, children: List[Tag]) extends TagClassifier(name, children: List[TagClassifier]){

  def getTags(tagClassifier: String): List[Tag] = ???
  def getTag(tagClassifier: String): Option[Tag] = ???

  def remove(tag: Tag): Unit = ???
  def add(tag: Tag): Unit  = ???

  def is(tagClassifier: TagClassifier): Boolean = ???

  def has(tagClassifier: TagClassifier): Boolean = ???

  def equals(name : String): Boolean = ???
  def equals(tag : Tag): Boolean = ??? //Reference equality
}

object Tag {
  def apply(name: String, children: List[Tag] = List()) = new Tag(name, children)
}

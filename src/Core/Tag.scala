package Core

/**
 * User: Callum
 * Date: 28/09/13
 * Time: 15:06
 */
class Tag(var tags: List[Tag]) {
  def addTag(tag: Tag) {
    tags = tag :: tags
  }
  def removeTag(tag: Tag) {
    tags = tags.diff(List(tag))
  }
}

//class DataTag[T](var value: T, tagList: List[Tag]) extends Tag(tagList)

class TaglessTag extends Tag(List())
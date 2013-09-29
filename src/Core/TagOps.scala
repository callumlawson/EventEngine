package Core

/**
 * User: Callum
 * Date: 28/09/13
 * Time: 18:51
 */
trait ITagOps{
  def AddTag(tagToAdd: Tag)(parent: Tag)
  def RemoveTag(tagToRemove: Tag)(parent: Tag)
}

object TagOps extends ITagOps{
  def AddTag(tagToAdd: Tag)(parent: Tag) {
    parent.addTag(tagToAdd)
  }

  def RemoveTag(tagToRemove: Tag)(parent: Tag) {
    parent.removeTag(tagToRemove)
  }
}

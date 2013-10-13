package Core

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import OldCore.{Tag, OrderedTagCollection}

/**
 * User: Callum
 * Date: 28/09/13
 * Time: 18:51
 */

trait ITagOps{
  def addTag(tagToAdd: Tag)(parent: Tag): Unit
  def removeTag(tagToRemove: Tag)(parent: Tag): Unit
  def hasTag[TagType](parent: Tag): Boolean
  def applyOpToTagAndChildren(tagOp: ITagOps): Unit

  def incrementTag(tag: Tag): Option[Tag] = ???
  def decrementTag(tag: Tag): Option[Tag] = ???
  def parent(tag: Tag): Option[Tag] = ???
}

object TagOps extends ITagOps{
  def addTag(tagToAdd: Tag)(parent: Tag){
    println("Add tag: " + tagToAdd + " to " + parent)
//    parent.addTag(tagToAdd)
  }

  def removeTag(tagToRemove: Tag)(parent: Tag) {
    println("Remove tag: " + tagToRemove + " from " + parent)
//    parent.removeTag(tagToRemove)
  }

  def getTag[TagType](parent: Tag): Option[Tag] = ???
//    parent.tags.find(tag => tag.isInstanceOf[TagType])
//  }

  def hasTag[TagType](parent: Tag): Boolean = ???
//    parent.tags.foldLeft(false)((last, next) => last || next.isInstanceOf[TagType])
//  }

  def applyOpToTagAndChildren(tagOp: ITagOps) {
    throw new NotImplementedException()
  }

//  def incrementTag[TagType <: OrderedTagCollection](parent: Tag) {
//    val tagToIncrement = getTag[TagType](parent)
//    println(tagToIncrement.asInstanceOf[OrderedTagCollection].ordering)
//  }
}

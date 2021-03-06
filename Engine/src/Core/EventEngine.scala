package Core

import Core.Tag

/**
 * User: Callum
 * Date: 06/10/13
 * Time: 19:31
 */
object EventEngine {
  def main(args: Array[String]) {
    new Setup()
  }
}

class Setup {
  val aTagClassifier = TagClassifier("Health", List(
    TagClassifier("Positive Health", List(Tag("Very Healthy"), Tag("Quite Health"))),
    TagClassifier("Negative Health", List(Tag("RedMist"))))
  )

  val aTag = Tag("bobby")

  if (aTag is Tag("Hungry"))
  if (aTag has Tag("Health"))

  TagClassifier("Loyalty", List(Tag("Very loyal"), Tag("Kinda loyal")))

  TagClassifier("Person", List(Tag("Bobby"), Tag("Jason")))
}
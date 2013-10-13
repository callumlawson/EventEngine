package Core

/**
 * Here is a message telling everyone that this is generated and not to be touched
 */
object Data {

  val tagHierarchies = TagClassifier("Any", List(

    TagClassifier("Health", List(
      TagClassifier("Positive Health", List(Tag("Very Healthy"), Tag("Quite Health"))),
      TagClassifier("Negative Health", List(Tag("RedMist"))))
    ),

    TagClassifier("Loyalty", List(Tag("Very loyal"), Tag("Kinda loyal"))),

    TagClassifier("Person", List(Tag("Bobby"), Tag("Jason")))

  ))
}

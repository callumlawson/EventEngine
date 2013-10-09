package Core

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
  TagClassifier("Health", List(
    TagClassifier("Positive Health", List(Tag("Very Healthy"), Tag("Quite Health"))),
    TagClassifier("Negative Health", List(Tag("RedMist"))))
  )
}
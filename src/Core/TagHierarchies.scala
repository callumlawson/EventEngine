package Core


sealed class PersonalityTag extends TaglessTag

object PersonalityTag {
  case class Liar() extends PersonalityTag

  case class WellSpoken() extends PersonalityTag

  case class Meddler() extends PersonalityTag
}

sealed abstract class Person(tagList: List[Tag]) extends Tag(tagList)

object Person {
  case class Joe(tagList: List[Tag]) extends Person(tagList)
  case class Bobby(tagList: List[Tag]) extends Person(tagList)
}


case class World(tagList: List[Tag]) extends Tag(tagList)



sealed abstract class HealthTag extends TaglessTag

object HealthTag {

  abstract class Positive() extends HealthTag

  case class VeryHealthy() extends Positive

  case class QuiteHealthy() extends Positive

  abstract class Negative() extends HealthTag

  case class NotVeryHealthy() extends Negative

  case class RedMist() extends Negative

  val Ordering: List[Tag] = List(VeryHealthy(), QuiteHealthy(), NotVeryHealthy(), RedMist())
}


sealed class Faction extends TaglessTag

case class BlackKnives() extends Faction

case class Clowns() extends Faction

sealed class Loyalty(faction: Faction) extends Tag(List(faction))

case class VeryLoyal(faction: Faction) extends Loyalty(faction)

case class ReadyToBackstab(faction: Faction) extends Loyalty(faction)


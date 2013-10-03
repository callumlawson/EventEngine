package Core

import scala.collection.mutable.Set

sealed abstract class PersonalityTag extends TaglessTag

object PersonalityTag {
  case class Liar() extends PersonalityTag

  case class WellSpoken() extends PersonalityTag

  case class Meddler() extends PersonalityTag
}

sealed class Person(tagSet: Set[Tag]) extends Tag(tagSet)
object Person {
  case class Joe(tagSet: Set[Tag] = Set()) extends Person(tagSet)
  case class Bobby(tagSet: Set[Tag] = Set()) extends Person(tagSet)
}

case class World(tagSet: Set[Tag] = Set()) extends Tag(tagSet)

abstract class HealthTag extends TaglessTag
  case class VeryHealthy() extends HealthTag

  case class QuiteHealthy() extends HealthTag

  case class NotVeryHealthy() extends HealthTag

  case class RedMist() extends HealthTag

//abstract class HealthTag extends TaglessTag
//object HealthTagCollection extends HealthTag with OrderedTagCollection{
//
//  abstract class Positive() extends HealthTag
//
//  case class VeryHealthy() extends Positive
//
//  case class QuiteHealthy() extends Positive
//
//  abstract class Negative() extends HealthTag
//
//  case class NotVeryHealthy() extends Negative
//
//  case class RedMist() extends Negative
//
//  def ordering: Seq[Tag] = List(VeryHealthy(), QuiteHealthy(), NotVeryHealthy(), RedMist())
//}

sealed class Faction extends TaglessTag

case class BlackKnives() extends Faction

case class Clowns() extends Faction

sealed class Loyalty(faction: Faction) extends Tag(Set(faction))

case class VeryLoyal(faction: Faction) extends Loyalty(faction)

case class ReadyToBackstab(faction: Faction) extends Loyalty(faction)


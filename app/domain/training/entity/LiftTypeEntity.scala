package domain.training.entity

import domain.{Action, Description, Id}

case class LiftTypeEntity(
    id: Option[Id],
    name: LiftTypeName,
    referenceUrl: Option[String],
    description: Option[Description],
    userId: Id,
    defaultAction: Action,
    shareFlag: Boolean = false
)

case class LiftTypeName(value: String) extends AnyVal
object LiftTypeName {
  def apply(value: String): Option[LiftTypeName] = Some(value).filter(validate).map(new LiftTypeName(_))

  def validate(value: String): Boolean = value.nonEmpty && value.length <= 50
}


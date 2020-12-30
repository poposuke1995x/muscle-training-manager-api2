package domain.training_menu.factory

import domain.training_menu.entity.{LiftTypeEntity, LiftTypeName}
import domain.{Action, Description, Id}

case class LiftTypeFactory(
    id: Option[Int],
    name: String,
    referenceUrl: Option[String],
    description: Option[String],
    userId: Int,
    defaultRep: Int = 0,
    defaultWeight: Int = 0,
    defaultSetCount: Int = 0,
    shareFlag: Boolean = false
)

object LiftTypeFactory {
  def apply(
      id: Option[Int],
      name: String,
      referenceUrl: Option[String],
      description: Option[String],
      userId: Int,
      defaultRep: Int = 0,
      defaultWeight: Int = 0,
      defaultSetCount: Int = 0,
      shareFlag: Boolean = false): Option[LiftTypeEntity] =
    LiftTypeName(name).flatMap(eName => Id(userId).map {
      eUserId =>
        LiftTypeEntity(
          id.flatMap(Id(_)),
          eName,
          referenceUrl,
          description.flatMap(Description(_)),
          eUserId,
          Action(defaultRep, defaultWeight, defaultSetCount),
          shareFlag
        )
    })
}
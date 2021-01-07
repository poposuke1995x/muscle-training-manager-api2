package domain.training.factory

import domain.training.entity.{LiftTypeEntity, LiftTypeName}
import domain.support.{Action, Description, Id}

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
    LiftTypeName(name).flatMap(eName => Id(userId).flatMap {
      eUserId =>
        Action(defaultRep, defaultWeight, defaultSetCount).map {
          action =>
            LiftTypeEntity(
              id.flatMap(Id(_)),
              eName,
              referenceUrl,
              description.flatMap(Description(_)),
              eUserId,
              action,
              shareFlag
            )
        }
    })
}
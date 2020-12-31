package domain.training.factory

import domain.training.entity.{TrainingMenuEntity, TrainingMenuName}
import domain.{Description, Id}

case class TrainingMenuFactory(
    id: Option[Int],
    name: String,
    description: Option[String],
    userId: Int,
    shareFlag: Boolean = false
)

object TrainingMenuFactory {
  def apply(
      id: Option[Int],
      name: String,
      description: Option[String],
      userId: Int,
      shareFlag: Boolean = false
  ): Option[TrainingMenuEntity] =
    TrainingMenuName(name).flatMap {
      entityName =>
        Id(userId).map {
          entityUserId =>
            TrainingMenuEntity(
              id.flatMap(Id(_)),
              entityName,
              description.flatMap(Description(_)),
              entityUserId,
              shareFlag
            )
        }
    }

}

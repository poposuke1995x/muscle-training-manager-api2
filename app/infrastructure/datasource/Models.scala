package infrastructure.datasource

import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.training.factory.{LiftTypeFactory, TrainingMenuFactory}
import domain.user.{UserEntity, UserFactory}

case class UserModel(id: Option[Int], name: String, uid: String) {
  def toEntity: Option[UserEntity] = UserFactory(id: Option[Int], name: String, uid: String)
}



case class LiftActionModel(
    id: Option[Int],
    liftTypeId: Int,
    trainingMenuId: Int,
    lightRep: Int = 0,
    lightWeight: Int = 0,
    lightSetCount: Int = 0,
    heavyRep: Int = 0,
    heavyWeight: Int = 0,
    heavySetCount: Int = 0,
)

case class TrainingMenuModel(
    id: Option[Int],
    name: String,
    description: Option[String],
    userId: Int,
    shareFlag: Boolean = false
) {
  def toEntity: Option[TrainingMenuEntity] = TrainingMenuFactory(
    id: Option[Int],
    name: String,
    description: Option[String],
    userId: Int,
    shareFlag: Boolean
  )
}



case class LiftTypeModel(
    id: Option[Int],
    name: String,
    referenceUrl: Option[String],
    description: Option[String],
    userId: Int,
    defaultRep: Int = 0,
    defaultWeight: Int = 0,
    defaultSetCount: Int = 0,
    shareFlag: Boolean = false
) {
  def toEntity: Option[LiftTypeEntity] = LiftTypeFactory(
    id: Option[Int],
    name: String,
    referenceUrl: Option[String],
    description: Option[String],
    userId: Int,
    defaultRep: Int,
    defaultWeight: Int,
    defaultSetCount: Int,
    shareFlag: Boolean
  )
}



case class TargetModel(
    id: Option[Int],
    liftActionId: Int,
    bodyPartId: Int,
    isMain: Boolean = true
)
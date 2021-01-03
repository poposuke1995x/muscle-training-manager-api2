package presentation

import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import infrastructure.datasource.TrainingMenuModel

case class BodyPartResponseModel(id: Option[Int], name: String, categoryId: Int)

case class CategoryResponseModel(id: Option[Int], name: String)

case class TrainingMenuResponseModel(
    id: Option[Int],
    name: String,
    description: Option[String],
    userId: Int,
    shareFlag: Boolean = false
)

object TrainingMenuResponseModel {
  def fromEntity(entity: TrainingMenuEntity): TrainingMenuModel = TrainingMenuModel(
    entity.id.map(_.value),
    entity.name.value,
    entity.description.map(_.value),
    entity.userId.value,
    entity.shareFlag
  )
}

case class LiftTypeResponseModel(
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

object LiftTypeResponseModel {
  def fromEntity(entity: LiftTypeEntity): LiftTypeResponseModel = LiftTypeResponseModel(
    entity.id.map(_.value),
    entity.name.value,
    entity.referenceUrl,
    entity.description.map(_.value),
    entity.userId.value,
    entity.defaultAction.rep.value,
    entity.defaultAction.weight.value,
    entity.defaultAction.setCount.value,
    entity.shareFlag: Boolean
  )
}

//object BodyPartResponseModel{
//  //  implicit val jsonConfig: Configuration = Configuration.default.withSnakeCaseMemberNames
//  //  implicit val userNameDecoder: Decoder[BodyPartResponseModel] = deriveConfiguredDecoder
//  //  implicit val userNameEncoder: Encoder[BodyPartResponseModel] = deriveConfiguredEncoder
//  implicit val encoder: Encoder[BodyPartResponseModel] = deriveEncoder
//  implicit val decoder: Decoder[BodyPartResponseModel] = deriveDecoder
//}
package presentation

import domain.training.entity.TrainingMenuEntity
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

object TrainingMenuResponseModel{
  def fromEntity(entity: TrainingMenuEntity): TrainingMenuModel = TrainingMenuModel(
    entity.id.map(_.value),
    entity.name.value,
    entity.description.map(_.value),
    entity.userId.value,
    entity.shareFlag
  )
}
//object BodyPartResponseModel{
//  //  implicit val jsonConfig: Configuration = Configuration.default.withSnakeCaseMemberNames
//  //  implicit val userNameDecoder: Decoder[BodyPartResponseModel] = deriveConfiguredDecoder
//  //  implicit val userNameEncoder: Encoder[BodyPartResponseModel] = deriveConfiguredEncoder
//  implicit val encoder: Encoder[BodyPartResponseModel] = deriveEncoder
//  implicit val decoder: Decoder[BodyPartResponseModel] = deriveDecoder
//}
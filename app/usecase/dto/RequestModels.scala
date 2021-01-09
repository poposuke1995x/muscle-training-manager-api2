package usecase.dto

import com.google.inject.Inject
import domain.support.{Error, Id, RequestError}
import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.training.factory.{LiftTypeFactory, TrainingMenuFactory}
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import usecase.query.LiftTypeQueryService

case class LiftTypeCreateRequestModel(
    name: String,
    referenceUrl: Option[String],
    description: Option[String],
    defaultRep: Int = 0,
    defaultWeight: Int = 0,
    defaultSetCount: Int = 0,
    shareFlag: Boolean = false
) {
  def toEntity(userId: Id): Either[Error, LiftTypeEntity] = LiftTypeFactory(
    None,
    name: String,
    referenceUrl: Option[String],
    description: Option[String],
    userId.value: Int,
    defaultRep: Int,
    defaultWeight: Int,
    defaultSetCount: Int,
    shareFlag: Boolean
  )
  match {
    case None => Left(RequestError())
    case Some(entity) => Right(entity)
  }
}

object LiftTypeCreateRequestModel {
  implicit val decoder: Decoder[LiftTypeCreateRequestModel] = deriveDecoder
}

case class LiftTypeUpdateRequestModel(
    name: Option[String],
    referenceUrl: Option[String],
    description: Option[String],
    defaultRep: Option[Int],
    defaultWeight: Option[Int],
    defaultSetCount: Option[Int],
    shareFlag: Option[Boolean]
) {
  def toEntity(beforeUpdateEntity: LiftTypeEntity): Either[Error, LiftTypeEntity] = {
    val updatingName: String = name.getOrElse(beforeUpdateEntity.name.value)
    val updatingReferenceUrl: Option[String] = referenceUrl match {
      case None => beforeUpdateEntity.referenceUrl
      case _ => this.referenceUrl
    }
    val updatingDescription: Option[String] = description match {
      case None => beforeUpdateEntity.referenceUrl
      case _ => this.description
    }

    val updatingRep = defaultRep.getOrElse(beforeUpdateEntity.defaultAction.rep.value)
    val updatingWeight = defaultWeight.getOrElse(beforeUpdateEntity.defaultAction.weight.value)
    val updatingSetCount = defaultSetCount.getOrElse(beforeUpdateEntity.defaultAction.setCount.value)
    LiftTypeFactory(
      beforeUpdateEntity.id.map(_.value),
      updatingName: String,
      updatingReferenceUrl: Option[String],
      updatingDescription: Option[String],
      beforeUpdateEntity.id.get.value: Int,
      updatingRep: Int,
      updatingWeight: Int,
      updatingSetCount: Int,
      shareFlag.getOrElse(beforeUpdateEntity.shareFlag): Boolean
    )
    match {
      case None => Left(RequestError())
      case Some(entity) => Right(entity)
    }
  }

}

object LiftTypeUpdateRequestModel {
  implicit val decoder: Decoder[LiftTypeUpdateRequestModel] = deriveDecoder
}

case class TrainingMenuRequestModel(
    name: String,
    description: Option[String],
    shareFlag: Boolean = false
) {
  def toEntity(userId: Id): Either[Error, TrainingMenuEntity] = TrainingMenuFactory(
    None,
    name,
    description,
    userId.value,
    shareFlag
  ) match {
    case None => Left(RequestError())
    case Some(entity) => Right(entity)
  }
}

object TrainingMenuRequestModel {
  implicit val decoder: Decoder[TrainingMenuRequestModel] = deriveDecoder
}



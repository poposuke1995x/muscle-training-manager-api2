package usecase.dto

import domain.support.{Error, Id, RequestError}
import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.training.factory.{LiftTypeFactory, TrainingMenuFactory}
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class LiftTypeRequestModel(
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

object LiftTypeRequestModel {
  implicit val decoder: Decoder[LiftTypeRequestModel] = deriveDecoder
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



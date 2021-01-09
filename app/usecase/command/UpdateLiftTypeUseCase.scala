package usecase.command

import com.google.inject.Inject
import domain.support.{DbError, EntityNotFoundError, Error, Id, RequestError}
import domain.training.entity.LiftTypeEntity
import domain.training.lifecycle.LiftTypeRepository
import domain.user.{FirebaseUid, UserService}
import usecase.dto.{LiftTypeResponseModel, LiftTypeUpdateRequestModel}
import usecase.query.LiftTypeQueryService

import scala.concurrent.{ExecutionContext, Future}

case class UpdateLiftTypeUseCase @Inject()(repository: LiftTypeRepository)
    (userService: UserService)
    (queryService: LiftTypeQueryService)
    (implicit ec: ExecutionContext) {

  def apply(uid: FirebaseUid)
      (liftTypeId: Id, model: LiftTypeUpdateRequestModel): Future[Either[Error, LiftTypeResponseModel]] =
    userService.getUser(uid).flatMap {
      case None => Future.successful(Left(EntityNotFoundError()))
      case Some(user) =>
        queryService.findById(liftTypeId)
            .map {
              _.filter(_.userId == user.id).map(model.toEntity)
            }
            .flatMap {
              case None => Future.successful(Left(DbError()))
              case Some(value) => value.map(update) match {
                case Left(_) => Future.successful(Left(RequestError()))
                case Right(result) => result
              }
            }

    }

  private def update(entity: LiftTypeEntity): Future[Either[RequestError, LiftTypeResponseModel]] =
    repository.update(entity).map {
      _.map(LiftTypeResponseModel.fromEntity) match {
        case None => Left(RequestError())
        case Some(model) => Right(model)
      }
    }

}

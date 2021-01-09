package usecase.command

import com.google.inject.Inject
import domain.support.{DbError, EntityNotFoundError, Error}
import domain.training.lifecycle.TrainingMenuRepository
import domain.user.{FirebaseUid, UserService}
import usecase.dto.{TrainingMenuRequestModel, TrainingMenuResponseModel}

import scala.concurrent.{ExecutionContext, Future}

case class CreateTrainingMenuUseCase @Inject()
(repository: TrainingMenuRepository)(userService: UserService)(implicit ec: ExecutionContext) {
  def apply(uid: FirebaseUid)(model: TrainingMenuRequestModel): Future[Either[Error, TrainingMenuResponseModel]] =
    userService.getUser(uid).flatMap {
      case None => Future.successful(Left(EntityNotFoundError()))
      case Some(user) => model.toEntity(user.id) match {
        case Left(error) => Future.successful(Left(error))
        case Right(liftTypeEntity) => user.createTrainingMenu(repository)(liftTypeEntity).map {
          case None => Left(DbError())
          case Some(liftTypeEntity) => Right(liftTypeEntity).map(TrainingMenuResponseModel.fromEntity)
        }
      }
    }
}

package usecase.command

import com.google.inject.Inject
import domain.support.{DbError, EntityNotFoundError, Error}
import domain.training.lifecycle.LiftTypeRepository
import domain.user.{FirebaseUid, UserService}
import usecase.dto.{LiftTypeRequestModel, LiftTypeResponseModel}

import scala.concurrent.{ExecutionContext, Future}

case class CreateLiftTypeUseCase @Inject()(repository: LiftTypeRepository)
    (userService: UserService)
    (implicit ec: ExecutionContext) {

  def apply(uid: FirebaseUid)(liftTypeRequestModel: LiftTypeRequestModel): Future[Either[Error, LiftTypeResponseModel]] =
    userService.getUser(uid).flatMap {
      case None => Future.successful(Left(EntityNotFoundError()))
      case Some(user) => liftTypeRequestModel.toEntity(user.id) match {
        case Left(error) => Future.successful(Left(error))
        case Right(liftTypeEntity) => user.createLiftType(repository)(liftTypeEntity).map {
          case None => Left(DbError())
          case Some(liftTypeEntity) => Right(liftTypeEntity).map(LiftTypeResponseModel.fromEntity)
        }
      }
    }


}

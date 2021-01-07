package usecase.command

import com.google.inject.Inject
import domain.support.Id
import domain.training.lifecycle.LiftTypeRepository
import domain.user.{FirebaseUid, UserService}

import scala.concurrent.{ExecutionContext, Future}

case class DeleteLiftTypeUseCase @Inject()(repository: LiftTypeRepository)
    (userService: UserService)
    (implicit ec: ExecutionContext){

  def apply(uid: FirebaseUid)(liftTypeId: Id): Future[Boolean] =
    userService.getUser(uid).flatMap {
      case None => Future.successful(false)
      case Some(user) => user.deleteLiftType(repository)(liftTypeId)
    }




}

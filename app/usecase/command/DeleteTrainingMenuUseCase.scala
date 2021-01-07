package usecase.command

import com.google.inject.Inject
import domain.support.Id
import domain.training.lifecycle.TrainingMenuRepository
import domain.user.{FirebaseUid, UserService}

import scala.concurrent.{ExecutionContext, Future}

case class DeleteTrainingMenuUseCase @Inject()(repository: TrainingMenuRepository)
    (userService: UserService)
    (implicit ec: ExecutionContext) {

  def apply(uid: FirebaseUid)(menuId: Id): Future[Boolean] =
    userService.getUser(uid).flatMap {
      case None => Future.successful(false)
      case Some(user) => user.deleteTrainingMenu(repository)(menuId)
    }


}

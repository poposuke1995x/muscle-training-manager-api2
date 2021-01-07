package usecase.query

import domain.support.Id
import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.user.FirebaseUid

import scala.concurrent.Future

trait TrainingMenuQueryService {

  def findByUserId(uid: FirebaseUid): Future[List[TrainingMenuEntity]]

  def findLiftTypes(uid: FirebaseUid)(menuId: Id): Future[List[LiftTypeEntity]]
}

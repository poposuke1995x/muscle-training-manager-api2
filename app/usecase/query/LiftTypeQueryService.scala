package usecase.query

import domain.support.Id
import domain.training.entity.LiftTypeEntity
import domain.user.FirebaseUid

import scala.concurrent.Future

trait LiftTypeQueryService {

  def findByUserId(uid: FirebaseUid): Future[List[LiftTypeEntity]]

  def findPublished(uid: FirebaseUid): Future[List[LiftTypeEntity]]

  def findById(id: Id): Future[Option[LiftTypeEntity]]
}

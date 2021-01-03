package presentation.query_service

import domain.Id
import domain.training.entity.LiftTypeEntity

import scala.concurrent.Future

trait LiftTypeQueryServiceInterface {

  def findByUserId(userId: Id): Future[List[LiftTypeEntity]]

  def findPublished(userId: Id): Future[List[LiftTypeEntity]]

  def findById(id: Id): Future[Option[LiftTypeEntity]]
}

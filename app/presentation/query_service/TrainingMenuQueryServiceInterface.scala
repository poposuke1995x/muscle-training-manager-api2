package presentation.query_service

import domain.Id
import domain.training.entity.TrainingMenuEntity

import scala.concurrent.Future

trait TrainingMenuQueryServiceInterface {

  def findByUserId(userId: Id): Future[List[TrainingMenuEntity]]
}

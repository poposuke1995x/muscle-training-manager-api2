package domain.training.lifecycle

import domain.support.Id
import domain.training.entity.TrainingMenuEntity

import scala.concurrent.Future

trait TrainingMenuRepository {
  def index: Future[List[TrainingMenuEntity]]

  def findById(id: Int): Future[Option[TrainingMenuEntity]]

  def findByUserId(userId: Int): Future[List[TrainingMenuEntity]]

  def insert(trainingMenu: TrainingMenuEntity): Future[Option[TrainingMenuEntity]]

  def update(trainingMenu: TrainingMenuEntity): Future[Option[TrainingMenuEntity]]

  def delete(menuId: Id)(userId: Id): Future[Boolean]
}

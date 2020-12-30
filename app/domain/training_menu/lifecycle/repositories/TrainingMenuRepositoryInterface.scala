package domain.training_menu.lifecycle.repositories

import domain.training_menu.lifecycle.TrainingMenuModel

import scala.concurrent.Future

trait TrainingMenuRepositoryInterface {
  def index(): Future[List[TrainingMenuModel]]

  def findById(id: Int): Future[Option[TrainingMenuModel]]

  def findByUserId(userId: Int): Future[List[TrainingMenuModel]]

  def insert(trainingMenu: TrainingMenuModel): Future[Option[TrainingMenuModel]]

  def update(trainingMenu: TrainingMenuModel): Future[Option[TrainingMenuModel]]

  def delete(id: Int): Future[Boolean]
}

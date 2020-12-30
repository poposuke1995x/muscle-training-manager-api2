package domain.training_menu.lifecycle.repositories

import domain.training_menu.lifecycle.LiftTypeModel

import scala.concurrent.Future

trait LiftTypeRepositoryInterface {
  def index(): Future[List[LiftTypeModel]]

  def findById(id: Int): Future[Option[LiftTypeModel]]

  def findByUserId(userId: Int): Future[List[LiftTypeModel]]

  def insert(liftType: LiftTypeModel): Future[Option[LiftTypeModel]]

  def update(liftType: LiftTypeModel): Future[Option[LiftTypeModel]]

  def updateDefaultAction(
      liftTypeId: Int,
      defaultRep: Int,
      defaultSetCount: Int,
      defaultWeight: Int): Future[Option[(Int, Int, Int, Int)]]

  def share(liftTypeId: Int): Future[Int]

  def delete(id: Int): Future[Boolean]
}

package domain.training.lifecycle

import domain.Id
import domain.training.entity.LiftTypeEntity

import scala.concurrent.Future

trait LiftTypeRepositoryInterface {
  def index: Future[List[LiftTypeEntity]]

  def findById(id: Id): Future[Option[LiftTypeEntity]]

  def findByUserId(userId: Int): Future[List[LiftTypeEntity]]

  def insert(liftType: LiftTypeEntity): Future[Option[LiftTypeEntity]]

  def update(liftType: LiftTypeEntity): Future[Option[LiftTypeEntity]]

  def updateDefaultAction(
      liftTypeId: Int,
      defaultRep: Int,
      defaultSetCount: Int,
      defaultWeight: Int): Future[Option[(Int, Int, Int, Int)]]

  def share(liftTypeId: Int): Future[Int]

  def delete(id: Int): Future[Boolean]
}

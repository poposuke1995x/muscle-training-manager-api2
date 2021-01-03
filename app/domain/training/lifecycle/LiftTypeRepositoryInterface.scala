package domain.training.lifecycle

import domain.training.entity.LiftTypeEntity

import scala.concurrent.Future

trait LiftTypeRepositoryInterface {
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

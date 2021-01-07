package domain.training.lifecycle

import domain.support.Id
import domain.training.entity.LiftTypeEntity

import scala.concurrent.Future

trait LiftTypeRepository {
  def insert(liftType: LiftTypeEntity): Future[Option[LiftTypeEntity]]

  def update(liftType: LiftTypeEntity): Future[Option[LiftTypeEntity]]

  def updateDefaultAction(
      liftTypeId: Int,
      defaultRep: Int,
      defaultSetCount: Int,
      defaultWeight: Int): Future[Option[(Int, Int, Int, Int)]]

  def share(liftTypeId: Id): Future[Int]

  def delete(liftTypeId: Id)(userId: Id): Future[Boolean]
}

package domain.user

import domain.support.Id
import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.training.lifecycle.{LiftTypeRepository, TrainingMenuRepository}

import scala.concurrent.Future

case class UserEntity(id: Id, name: UserName, uid: FirebaseUid) {
  def createLiftType(repository: LiftTypeRepository)(liftTypeEntity: LiftTypeEntity): Future[Option[LiftTypeEntity]] =
    repository.insert(liftTypeEntity)

  def createTrainingMenu(repository: TrainingMenuRepository)
      (menu: TrainingMenuEntity): Future[Option[TrainingMenuEntity]] = repository.insert(menu)

  def deleteLiftType(repository: LiftTypeRepository)
      (liftTypeId: Id): Future[Boolean] = repository.delete(liftTypeId)(this.id)

  def deleteTrainingMenu(repository: TrainingMenuRepository)(menuId: Id): Future[Boolean] = repository.delete(menuId)(this.id)

  //
  //  def registerLiftTypesToMenu = ???
}


case class UserName(value: String) extends AnyVal

object UserName {
  def apply(value: String): Option[UserName] = Some(value).filter(validate).map(new UserName(_))

  def validate(value: String): Boolean = value.nonEmpty && value.length <= 20
}

case class FirebaseUid(value: String) extends AnyVal

object FirebaseUid {
  def apply(value: String): Option[FirebaseUid] = Some(value).filter(validate).map(new FirebaseUid(_))

  def validate(value: String): Boolean = value.nonEmpty
}

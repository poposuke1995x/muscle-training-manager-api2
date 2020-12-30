package domain.user

import domain.Id
import domain.training_menu.lifecycle.LiftTypeModel
import domain.training_menu.lifecycle.repositories.LiftTypeRepositoryInterface

import scala.concurrent.Future

case class UserEntity(id: Id, name: UserName, uid: FirebaseUid) {
  def createLiftType(repository: LiftTypeRepositoryInterface)(liftTypeModel: LiftTypeModel): Future[Option[LiftTypeModel]] =
    repository.insert(liftTypeModel)
//
//  def createTrainingMenu = ???
//
//  def deleteTrainingMenu = ???
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

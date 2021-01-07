package domain.user.lifecycle

import domain.support.Id
import domain.user.{FirebaseUid, UserEntity, UserName}

import scala.concurrent.Future

trait UserRepository {
  def findIdByUid(uid: FirebaseUid): Future[Option[Id]]
  def findUserByUid(uid: FirebaseUid): Future[Option[UserEntity]]
  def insert(name: UserName, uid: FirebaseUid): Future[Option[UserEntity]]
}

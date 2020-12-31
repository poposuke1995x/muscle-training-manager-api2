package domain.user.lifecycle

import domain.Id
import domain.user.{FirebaseUid, UserEntity, UserName}

import scala.concurrent.Future

trait UserRepositoryInterface {
  def findIdByUid(uid: FirebaseUid): Future[Option[Id]]
  def insert(name: UserName, uid: FirebaseUid): Future[Option[UserEntity]]
}

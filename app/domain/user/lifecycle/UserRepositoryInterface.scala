package domain.user.lifecycle

import domain.Id
import domain.user.FirebaseUid

import scala.concurrent.Future

trait UserRepositoryInterface {
  def findIdByUid(uid: FirebaseUid): Future[Option[Id]]
  def insert(user: UserModel): Future[Option[UserModel]]
}

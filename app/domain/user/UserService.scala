package domain.user

import com.google.inject.Inject
import domain.support.Id
import domain.user.lifecycle.UserRepository

import scala.concurrent.{ExecutionContext, Future}

case class UserService @Inject()(repository: UserRepository)(implicit ec: ExecutionContext) {

  def createGuestUser(uid: FirebaseUid): Future[Option[Id]] =
    repository.insert(UserName("guest").get, uid).map(_.map(_.id))

  def getUserId(uid: FirebaseUid): Future[Option[Id]] = repository.findIdByUid(uid)

  def getUser(uid: FirebaseUid): Future[Option[UserEntity]] = repository.findUserByUid(uid)

}


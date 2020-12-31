package domain.user

import com.google.inject.Inject
import domain.Id
import domain.user.lifecycle.UserRepositoryInterface

import scala.concurrent.{ExecutionContext, Future}

case class UserService @Inject()(repository: UserRepositoryInterface)(implicit ec: ExecutionContext) {

  def createGuestUser(uid: FirebaseUid): Future[Option[Id]] =
    repository.insert(UserName("guest").get, uid).map(_.map(_.id))

  def getUserId(uid: FirebaseUid): Future[Option[Id]] = repository.findIdByUid(uid)

}


package domain.user

import com.google.inject.Inject
import domain.Id
import domain.user.lifecycle.{UserModel, UserRepositoryInterface}

import scala.concurrent.{ExecutionContext, Future}

case class UserService @Inject()(repository: UserRepositoryInterface)(implicit ec: ExecutionContext) {

  def createGuestUser(uid: FirebaseUid): Future[Option[UserEntity]] =
    repository.insert {
      UserModel(None, "guest", uid.value)
    }.map {
      _.flatMap(userModel => UserFactory(userModel.id.get, userModel.name, userModel.uid))
    }

  def getUserId(uid: FirebaseUid): Future[Option[Id]] = repository.findIdByUid(uid)

}


package infrastructure.datasource.repository

import com.google.inject.Inject
import domain.Id
import domain.user.FirebaseUid
import domain.user.lifecycle.{UserModel, UserRepositoryInterface}
import infrastructure.datasource.Models
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UserRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, models: Models)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with UserRepositoryInterface {

  import profile.api._

  private val Users = TableQuery[models.UsersTable]

  def findIdByUid(uid: FirebaseUid): Future[Option[Id]] = db.run(
    Users.filter(_.firebase_uid === uid.value).map(_.id).result.headOption
  ).map {
    _.flatten.flatMap(Id(_))
  }

  def insert(user: UserModel): Future[Option[UserModel]] = Option(db.run((Users returning Users) += user)) match {
    case Some(user) => user.map(Some(_))
    case None => Future.successful(None)
  }

}
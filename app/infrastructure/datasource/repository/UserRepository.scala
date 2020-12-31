package infrastructure.datasource.repository

import com.google.inject.Inject
import domain.Id
import domain.user.lifecycle.UserRepositoryInterface
import domain.user.{FirebaseUid, UserEntity, UserName}
import infrastructure.datasource.{Tables, UserModel}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UserRepository @Inject()(tables: Tables)
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with UserRepositoryInterface {

  import profile.api._

  private val Users = TableQuery[tables.UsersTable]


  def findIdByUid(uid: FirebaseUid): Future[Option[Id]] = db.run(
    Users.filter(_.firebase_uid === uid.value).map(_.id).result.headOption
  ).map {
    _.flatten.flatMap(Id(_))
  }

  def insert(name: UserName, uid: FirebaseUid): Future[Option[UserEntity]] =
    Option {
      db.run(Users returning Users += UserModel(None, name.value, uid.value))
    } match {
      case None => Future.successful(None)
      case Some(user) => user.map(_.toEntity)
    }

}
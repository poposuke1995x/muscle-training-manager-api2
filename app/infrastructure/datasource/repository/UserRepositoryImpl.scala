package infrastructure.datasource.repository

import com.google.inject.Inject
import domain.support.Id
import domain.user.lifecycle.UserRepository
import domain.user.{FirebaseUid, UserEntity, UserName}
import infrastructure.datasource.{Tables, UserModel}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UserRepositoryImpl @Inject()(tables: Tables)
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with UserRepository {

  import profile.api._

  private val Users = TableQuery[tables.UsersTable]


  def findIdByUid(uid: FirebaseUid): Future[Option[Id]] = db.run(
    Users.filter(_.firebase_uid === uid.value).map(_.id).result.headOption
  ).map {
    _.flatten.flatMap(Id(_))
  }

  def findUserByUid(uid: FirebaseUid): Future[Option[UserEntity]] = db.run(
    Users.filter(_.firebase_uid === uid.value).result.headOption
  ).map(_.flatMap(_.toEntity))

  def insert(name: UserName, uid: FirebaseUid): Future[Option[UserEntity]] =
    Option {
      db.run(Users returning Users += UserModel(None, name.value, uid.value))
    } match {
      case None => Future.successful(None)
      case Some(user) => user.map(_.toEntity)
    }

}
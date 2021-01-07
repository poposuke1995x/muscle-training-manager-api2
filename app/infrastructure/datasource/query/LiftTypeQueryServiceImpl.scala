package infrastructure.datasource.query

import com.google.inject.Inject
import domain.support.Id
import domain.training.entity.LiftTypeEntity
import domain.user.FirebaseUid
import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import usecase.query.LiftTypeQueryService
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class LiftTypeQueryServiceImpl @Inject()(tables: Tables)
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with LiftTypeQueryService {

  import profile.api._

  private val LiftTypes = TableQuery[tables.LiftTypesTable]
  private val Users = TableQuery[tables.UsersTable]

  def findByUserId(uid: FirebaseUid): Future[List[LiftTypeEntity]] =
    db.run {
      for {
        userId <- Users.filter(_.firebase_uid === uid.value).map(_.id.get).result
        result <- LiftTypes.filter(_.userId inSetBind userId).result
      } yield result.toList.map(_.toEntity.get)
    }


  def findPublished(uid: FirebaseUid): Future[List[LiftTypeEntity]] =
    db.run {
      for {
        userId <- Users.filter(_.firebase_uid === uid.value).map(_.id.get).result
        result <- LiftTypes.filterNot(_.userId inSetBind userId).filter(_.shareFlag).result
      } yield result.toList.map(_.toEntity.get)
    }


  def findById(id: Id): Future[Option[LiftTypeEntity]] =
    db.run(LiftTypes.filter(_.id === id.value).result.headOption).map(_.flatMap(_.toEntity))
}

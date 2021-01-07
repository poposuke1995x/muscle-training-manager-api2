package infrastructure.datasource.query

import com.google.inject.Inject
import domain.support.Id
import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.user.FirebaseUid
import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import usecase.query.TrainingMenuQueryService
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuQueryServiceImpl @Inject()(tables: Tables)
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with TrainingMenuQueryService {

  import profile.api._

  private val TrainingMenu = TableQuery[tables.TrainingMenuTable]
  private val Actions = TableQuery[tables.LiftActionsTable]
  private val LiftTypes = TableQuery[tables.LiftTypesTable]
  private val Users = TableQuery[tables.UsersTable]


  def findByUserId(uid: FirebaseUid): Future[List[TrainingMenuEntity]] =
    db.run {
      for {
        userId <- Users.filter(_.firebase_uid === uid.value).map(_.id.get).result
        result <- TrainingMenu.filter(_.userId inSetBind userId).result
      } yield result.toList.map(_.toEntity.get)
    }

  def findLiftTypes(uid: FirebaseUid)(menuId: Id): Future[List[LiftTypeEntity]] =
    db.run {
      for {
        userId <- Users.filter(_.firebase_uid === uid.value).map(_.id.get).result
        menuId <- TrainingMenu
            .filter(_.userId inSetBind userId)
            .filter(_.id === menuId.value)
            .map(_.id.get)
            .result
        liftTypeIds <- Actions
            .filter(_.trainingMenuId inSetBind menuId)
            .map(_.liftTypeId)
            .result
        liftTypes <- LiftTypes
            .filter(_.id inSetBind liftTypeIds)
            .result
      } yield liftTypes.toList.map(_.toEntity.get)
    }
}

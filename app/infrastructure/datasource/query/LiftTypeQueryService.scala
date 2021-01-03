package infrastructure.datasource.query

import com.google.inject.Inject
import domain.Id
import domain.training.entity.LiftTypeEntity
import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import presentation.query_service.LiftTypeQueryServiceInterface
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class LiftTypeQueryService @Inject()(tables: Tables)
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with LiftTypeQueryServiceInterface {

  import profile.api._

  private val LiftTypes = TableQuery[tables.LiftTypesTable]

  def findByUserId(userId: Id): Future[List[LiftTypeEntity]] =
    db.run(LiftTypes.filter(_.userId === userId.value).result).map(_.toList).map(_.map(_.toEntity.get))

  def findPublished(userId: Id): Future[List[LiftTypeEntity]] =
    db.run(LiftTypes.filterNot(_.userId === userId.value).filter(_.shareFlag).result).map(_.toList).map(_.map(_.toEntity.get))

  def findById(id: Id): Future[Option[LiftTypeEntity]] =
    db.run(LiftTypes.filter(_.id === id.value).result.headOption).map(_.flatMap(_.toEntity))
}

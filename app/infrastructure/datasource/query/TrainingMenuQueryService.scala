package infrastructure.datasource.query

import com.google.inject.Inject
import domain.Id
import domain.training.entity.TrainingMenuEntity
import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import presentation.query_service.TrainingMenuQueryServiceInterface
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuQueryService @Inject()(tables: Tables)
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with TrainingMenuQueryServiceInterface {

  import profile.api._

  private val TrainingMenu = TableQuery[tables.TrainingMenuTable]

  def findByUserId(userId: Id): Future[List[TrainingMenuEntity]] = db.run(TrainingMenu.filter(_.userId === userId.value).result).map(_.toList).map(_.map(_.toEntity.get))

}

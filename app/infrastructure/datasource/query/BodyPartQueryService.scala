package infrastructure.datasource.query

import com.google.inject.Inject
import infrastructure.datasource.Models
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import presentation.query_service.{BodyPartQueryServiceInterface, BodyPartResponseModel}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class BodyPartQueryService @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, models: Models)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with BodyPartQueryServiceInterface {

  import profile.api._

  private val BodyParts = TableQuery[models.BodyPartsTable]

  def index: Future[List[BodyPartResponseModel]] = db.run(BodyParts.result).map(_.toList)

  def findById(id: Int): Future[Option[BodyPartResponseModel]] = db.run(BodyParts.filter(_.id === id).result.headOption)

}
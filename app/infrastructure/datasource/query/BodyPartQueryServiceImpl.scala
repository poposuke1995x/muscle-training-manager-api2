package infrastructure.datasource.query

import com.google.inject.Inject
import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import usecase.query.BodyPartQueryService
import slick.jdbc.JdbcProfile
import usecase.dto.BodyPartResponseModel

import scala.concurrent.{ExecutionContext, Future}

class BodyPartQueryServiceImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(tables: Tables)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with BodyPartQueryService {

  import profile.api._

  private val BodyParts = TableQuery[tables.BodyPartsTable]

  def index: Future[List[BodyPartResponseModel]] = db.run(BodyParts.result).map(_.toList)

  def findById(id: Int): Future[Option[BodyPartResponseModel]] = db.run(BodyParts.filter(_.id === id).result.headOption)

}
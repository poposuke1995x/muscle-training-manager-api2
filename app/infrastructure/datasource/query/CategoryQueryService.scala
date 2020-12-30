package infrastructure.datasource.query

import infrastructure.datasource.Models
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import presentation.query_service.{CategoryQueryServiceInterface, CategoryResponseModel}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CategoryQueryService @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, models: Models)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with CategoryQueryServiceInterface {

  import profile.api._

  private val Categories = TableQuery[models.CategoriesTable]

  def index: Future[List[CategoryResponseModel]] = db.run(Categories.result).map(_.toList)

  def findById(id: Int): Future[Option[CategoryResponseModel]] = db.run(Categories.filter(_.id === id).result.headOption)


}
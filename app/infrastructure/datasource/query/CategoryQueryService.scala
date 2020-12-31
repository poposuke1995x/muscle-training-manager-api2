package infrastructure.datasource.query


import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import presentation.CategoryResponseModel
import presentation.query_service.CategoryQueryServiceInterface
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CategoryQueryService @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(tables: Tables)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with CategoryQueryServiceInterface {

  import profile.api._

  private val Categories = TableQuery[tables.CategoriesTable]

  def index: Future[List[CategoryResponseModel]] = db.run(Categories.result).map(_.toList)

  def findById(id: Int): Future[Option[CategoryResponseModel]] = db.run(Categories.filter(_.id === id).result.headOption)


}
package infrastructure.datasource.query


import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import usecase.dto.CategoryResponseModel
import usecase.query.CategoryQueryService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CategoryQueryServiceImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(tables: Tables)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with CategoryQueryService {

  import profile.api._

  private val Categories = TableQuery[tables.CategoriesTable]

  def index: Future[List[CategoryResponseModel]] = db.run(Categories.result).map(_.toList)

  def findById(id: Int): Future[Option[CategoryResponseModel]] = db.run(Categories.filter(_.id === id).result.headOption)


}
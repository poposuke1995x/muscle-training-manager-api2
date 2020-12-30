package infrastructure.datasource.repository

import domain.training_menu.lifecycle.TrainingMenuModel
import domain.training_menu.lifecycle.repositories.TrainingMenuRepositoryInterface
import infrastructure.datasource.Models
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, models: Models)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with TrainingMenuRepositoryInterface {

  import profile.api._

  private val TrainingMenuObj = TableQuery[models.TrainingMenuTable]

  def index(): Future[List[TrainingMenuModel]] = db.run(TrainingMenuObj.result).map(_.toList)

  def findById(id: Int): Future[Option[TrainingMenuModel]] = db.run(TrainingMenuObj.filter(_.id === id).result.headOption)

  def findByUserId(userId: Int): Future[List[TrainingMenuModel]] = db.run(
    TrainingMenuObj.filter(_.userId === userId).result
  ).map(_.toList)

  def insert(trainingMenu: TrainingMenuModel): Future[Option[TrainingMenuModel]] = Option(db.run((TrainingMenuObj returning TrainingMenuObj) += trainingMenu)) match {
    case Some(menu) => menu.map(Some(_))
    case None => Future.successful(None)
  }

  def update(trainingMenu: TrainingMenuModel): Future[Option[TrainingMenuModel]] = db.run(TrainingMenuObj.filter(_.id === trainingMenu.id).update(trainingMenu)).map {
    case 0 => None
    case _ => Some(trainingMenu)
  }

  def delete(id: Int): Future[Boolean] = db.run(TrainingMenuObj.filter(_.id === id).delete).map {
    case 0 => false
    case _ => true
  }


}
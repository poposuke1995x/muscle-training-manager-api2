package infrastructure.datasource.repository

import domain.support.Id
import domain.training.entity.TrainingMenuEntity
import domain.training.lifecycle.TrainingMenuRepository
import infrastructure.datasource.Tables
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(tables: Tables)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with TrainingMenuRepository {

  import profile.api._

  private val TrainingMenuObj = TableQuery[tables.TrainingMenuTable]

  def index: Future[List[TrainingMenuEntity]] = db.run(TrainingMenuObj.result).map(_.toList).map(_.map(_.toEntity.get))

  def findById(id: Int): Future[Option[TrainingMenuEntity]] = db.run(TrainingMenuObj.filter(_.id === id).result.headOption).map(_.flatMap(_.toEntity))

  def findByUserId(userId: Int): Future[List[TrainingMenuEntity]] = db.run(
    TrainingMenuObj.filter(_.userId === userId).result
  ).map(_.toList).map(_.map(_.toEntity.get))

  def insert(trainingMenu: TrainingMenuEntity): Future[Option[TrainingMenuEntity]] =
    Option(db.run((TrainingMenuObj returning TrainingMenuObj) += tables.TrainingMenuTable.fromEntity(trainingMenu))) match {
      case Some(menu) => menu.map(_.toEntity)
      case None => Future.successful(None)
    }

  def update(trainingMenu: TrainingMenuEntity): Future[Option[TrainingMenuEntity]] =
    db.run(TrainingMenuObj.filter(_.id === trainingMenu.id.get.value).update(tables.TrainingMenuTable.fromEntity(trainingMenu))).map {
      case 0 => None
      case _ => Some(trainingMenu)
    }

  def delete(menuId: Id)(userId: Id): Future[Boolean] =
    db.run(TrainingMenuObj.filter(_.id === menuId.value).filter(_.userId === userId.value).delete).map {
      case 0 => false
      case _ => true
    }


}
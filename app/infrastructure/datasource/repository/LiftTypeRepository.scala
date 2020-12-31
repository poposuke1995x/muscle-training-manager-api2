package infrastructure.datasource.repository

import com.google.inject.Inject
import domain.Id
import domain.training.entity.LiftTypeEntity
import domain.training.lifecycle.repositories.LiftTypeRepositoryInterface
import infrastructure.datasource.{LiftTypeModel, Tables}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class LiftTypeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(tables: Tables)
    (implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] with LiftTypeRepositoryInterface {

  import profile.api._

  private val LiftTypes = TableQuery[tables.LiftTypesTable]

  def index: Future[List[LiftTypeEntity]] = db.run(LiftTypes.result).map(_.toList).map(_.map(_.toEntity.get))

  def findById(id: Id): Future[Option[LiftTypeEntity]] =
    db.run(LiftTypes.filter(_.id === id.value).result.headOption).map(_.flatMap(_.toEntity))

  def findByUserId(userId: Int): Future[List[LiftTypeEntity]] =
    db.run(LiftTypes.filter(_.userId === userId).result).map(_.toList).map(_.map(_.toEntity.get))

  def insert(liftType: LiftTypeEntity): Future[Option[LiftTypeEntity]] = Option {
    db.run(LiftTypes returning LiftTypes += tables.LiftTypesTable.fromEntity(liftType))
  } match {
    case None => Future.successful(None)
    case Some(liftType) => liftType.map(_.toEntity)
  }

  def update(liftType: LiftTypeEntity): Future[Option[LiftTypeEntity]] =
    db.run(LiftTypes.filter(_.id === liftType.id.get.value).update(tables.LiftTypesTable.fromEntity(liftType))).map {
      case 0 => None
      case _ => Some(liftType)
    }

  def updateDefaultAction(
      liftTypeId: Int,
      defaultRep: Int,
      defaultSetCount: Int,
      defaultWeight: Int): Future[Option[(Int, Int, Int, Int)]] =
    db.run(
      LiftTypes
          .filter(_.id === liftTypeId)
          .map(liftType => (liftType.defaultWeight, liftType.defaultRep, liftType.defaultSetCount))
          .update((defaultWeight, defaultRep, defaultSetCount))
    ).map {
      case 0 => None
      case _ => Some(
        liftTypeId,
        defaultRep,
        defaultSetCount,
        defaultWeight)
    }

  def share(liftTypeId: Int): Future[Int] = db.run(LiftTypes.filter(_.id === liftTypeId).map(_.shareFlag).update(true))

  def delete(id: Int): Future[Boolean] = db.run(LiftTypes.filter(_.id === id).delete).map {
    case 0 => false
    case _ => true
  }
}
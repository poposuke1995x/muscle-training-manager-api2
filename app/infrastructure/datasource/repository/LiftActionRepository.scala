//package infrastructure.datasource.repository
//
//import infrastructure.datasource.Tables
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.JdbcProfile
//
//import javax.inject.Inject
//import scala.concurrent.{ExecutionContext, Future}
//
//class LiftActionRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, models: Tables)
//    (implicit ec: ExecutionContext)
//    extends HasDatabaseConfigProvider[JdbcProfile] with LiftActionRepositoryInterface {
//
//  import profile.api._
//
//  private val LiftActions = TableQuery[models.LiftActionsTable]
//
//  def index(): Future[List[LiftAction]] = db.run(LiftActions.result).map(_.toList)
//
//  def findById(id: Int): Future[Option[LiftAction]] = db.run(LiftActions.filter(_.id === id).result.headOption)
//
//  def findByForeignKeyId(liftTypeId: Int, menuId: Int): Future[Option[LiftAction]] = db.run(
//    LiftActions
//        .filter(_.liftTypeId === liftTypeId)
//        .filter(_.trainingMenuId === menuId)
//        .result.headOption
//  )
//
//  def insert(liftAction: LiftAction): Future[Option[LiftAction]] =Option( db.run(LiftActions returning LiftActions += liftAction)) match {
//    case Some(liftAction) => liftAction.map(Some(_))
//    case None => Future.successful(None)
//  }
//
//  def update(liftAction: LiftAction): Future[Option[LiftAction]] = db.run(LiftActions.filter(_.id === liftAction.id).update(liftAction)).map {
//    case 0 => None
//    case _ => Some(liftAction)
//  }
//
//  def delete(id: Int): Future[Boolean] = db.run(LiftActions.filter(_.id === id).delete).map {
//    case 0 => false
//    case _ => true
//  }
//
//
//}
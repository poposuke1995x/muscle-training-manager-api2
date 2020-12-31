//package infrastructure.composite
//
//import com.google.inject.Inject
//import infrastructure.datasource.Tables
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.JdbcProfile
//
//import scala.concurrent.{ExecutionContext, Future}
//
//class ListLiftTypeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, models: Tables)
//    (implicit executionContext: ExecutionContext)
//    extends HasDatabaseConfigProvider[JdbcProfile] with ListLiftTypeRepositoryInterface {
//
//  import profile.api._
//
//  private val LiftTypes = TableQuery[models.LiftTypesTable]
//  private val LiftActions = TableQuery[models.LiftActionsTable]
//  private val Targets = TableQuery[models.TargetsTable]
//
//  override def execute(bodyPartId: Option[Int]): Future[Seq[LiftType]] = {
//    bodyPartId.getOrElse(0) match {
//      case bodyPartId if bodyPartId <= 0 => db.run(LiftTypes.filter(_.shareFlag).result)
//      case bodyPartId if bodyPartId > 0 => db.run(for {
//        targets <- Targets.filter(_.bodyPartId === bodyPartId).result
//        liftActions <- LiftActions.filter(_.id inSetBind targets.map(_.liftActionId)).result
//        liftTypes <- LiftTypes
//            .filter(_.id inSetBind liftActions.map(_.liftTypeId))
//            .filter(_.shareFlag)
//            .result
//      } yield liftTypes)
//    }
//
//  }
//
//
//}
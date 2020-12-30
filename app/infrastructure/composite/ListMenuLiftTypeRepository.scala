//package infrastructure.composite
//
//import com.google.inject.Inject
//import infrastructure.datasource.Models
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.JdbcProfile
//
//import scala.concurrent.{ExecutionContext, Future}
//
//class ListMenuLiftTypeRepository @Inject()
//(protected val dbConfigProvider: DatabaseConfigProvider, models: Models)(implicit executionContext: ExecutionContext)
//    extends HasDatabaseConfigProvider[JdbcProfile] with ListMenuLiftTypeRepositoryInterface {
//
//  import profile.api._
//
//  private val LiftActions = TableQuery[models.LiftActionsTable]
//  private val LiftTypes = TableQuery[models.LiftTypesTable]
//  private val Targets = TableQuery[models.TargetsTable]
//
//  def execute(trainingMenuId: Int, bodyPartId: Option[Int]): Future[(Seq[LiftType], Seq[LiftAction])] = db.run(
//    (for {
//      targets <- Targets.filter(_.bodyPartId === bodyPartId).result
//
//      liftActions <- bodyPartId.getOrElse(0) match {
//        case bodyPartId if bodyPartId <= 0 =>
//          LiftActions
//              .filter(_.trainingMenuId === trainingMenuId)
//              .result
//        case bodyPartId if bodyPartId > 0 =>
//          LiftActions
//              .filter(_.id inSetBind targets.map(_.liftActionId))
//              .filter(_.trainingMenuId === trainingMenuId)
//              .result
//      }
//
//      liftTypes <- LiftTypes.filter(_.id inSetBind liftActions.map(_.liftTypeId)).result
//    } yield (liftTypes, liftActions)).transactionally
//  )
//}

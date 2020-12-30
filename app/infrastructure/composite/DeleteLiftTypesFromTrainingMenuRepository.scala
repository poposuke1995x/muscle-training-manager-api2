//package infrastructure.composite
//
//import infrastructure.datasource.Models
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.JdbcProfile
//
//import javax.inject.Inject
//import scala.concurrent.{ExecutionContext, Future}
//
//class DeleteLiftTypesFromTrainingMenuRepository @Inject()(
//    protected val dbConfigProvider: DatabaseConfigProvider,
//    models: Models)(implicit executionContext: ExecutionContext)
//    extends HasDatabaseConfigProvider[JdbcProfile] with DeleteLiftTypesFromTrainingMenuRepositoryInterface {
//
//  import profile.api._
//
//  private val LiftActions = TableQuery[models.LiftActionsTable]
//
//  def execute(trainingMenuId: Int, liftTypeIds: List[Int]): Future[Boolean] =
//    db.run(
//      LiftActions
//          .filter(_.trainingMenuId === trainingMenuId)
//          .filter(_.liftTypeId inSetBind liftTypeIds)
//          .delete
//    ).map {
//      case 0 => false
//      case _ => true
//    }
//}
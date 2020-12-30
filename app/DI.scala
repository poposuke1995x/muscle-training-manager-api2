import com.google.inject.AbstractModule
import domain.training_menu.lifecycle.repositories.{LiftTypeRepositoryInterface, TrainingMenuRepositoryInterface}
import domain.user.lifecycle.UserRepositoryInterface
import infrastructure.datasource.query.{BodyPartQueryService, CategoryQueryService}
import infrastructure.datasource.repository.{LiftTypeRepository, TrainingMenuRepository, UserRepository}
import presentation.query_service.{BodyPartQueryServiceInterface, CategoryQueryServiceInterface}


class DI extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[BodyPartQueryServiceInterface]).to(classOf[BodyPartQueryService])
    bind(classOf[CategoryQueryServiceInterface]).to(classOf[CategoryQueryService])
    bind(classOf[UserRepositoryInterface]).to(classOf[UserRepository])
    bind(classOf[LiftTypeRepositoryInterface]).to(classOf[LiftTypeRepository])
    bind(classOf[TrainingMenuRepositoryInterface]).to(classOf[TrainingMenuRepository])
//    bind(classOf[LiftActionRepositoryInterface]).to(classOf[LiftActionRepository])
//    bind(classOf[RegisterLiftTypesToTrainingMenuRepositoryInterface]).to(classOf[RegisterLiftTypesToTrainingMenuRepository])
//    bind(classOf[DeleteLiftTypesFromTrainingMenuRepositoryInterface]).to(classOf[DeleteLiftTypesFromTrainingMenuRepository])
//    bind(classOf[ListLiftTypeRepositoryInterface]).to(classOf[ListLiftTypeRepository])
//    bind(classOf[ListMenuLiftTypeRepositoryInterface]).to(classOf[ListMenuLiftTypeRepository])
  }
}

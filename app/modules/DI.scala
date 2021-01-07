package modules

import com.google.inject.AbstractModule
import domain.training.lifecycle.{LiftTypeRepository, TrainingMenuRepository}
import domain.user.lifecycle.UserRepository
import infrastructure.datasource.query.{BodyPartQueryServiceImpl, CategoryQueryServiceImpl, LiftTypeQueryServiceImpl, TrainingMenuQueryServiceImpl}
import infrastructure.datasource.repository.{LiftTypeRepositoryImpl, TrainingMenuRepositoryImpl, UserRepositoryImpl}
import usecase.query.{BodyPartQueryService, CategoryQueryService, LiftTypeQueryService, TrainingMenuQueryService}


class DI extends AbstractModule {
  override def configure(): Unit = {
    queryServiceConfigure()
    repositoryConfigure()
  }

  private def queryServiceConfigure(): Unit = {
    bind(classOf[BodyPartQueryService]).to(classOf[BodyPartQueryServiceImpl])
    bind(classOf[CategoryQueryService]).to(classOf[CategoryQueryServiceImpl])
    bind(classOf[TrainingMenuQueryService]).to(classOf[TrainingMenuQueryServiceImpl])
    bind(classOf[LiftTypeQueryService]).to(classOf[LiftTypeQueryServiceImpl])
  }

  private def repositoryConfigure(): Unit = {
    bind(classOf[UserRepository]).to(classOf[UserRepositoryImpl])
    bind(classOf[LiftTypeRepository]).to(classOf[LiftTypeRepositoryImpl])
    bind(classOf[TrainingMenuRepository]).to(classOf[TrainingMenuRepositoryImpl])
    //    bind(classOf[LiftActionRepositoryInterface]).to(classOf[LiftActionRepository])
    //    bind(classOf[RegisterLiftTypesToTrainingMenuRepositoryInterface]).to(classOf[RegisterLiftTypesToTrainingMenuRepository])
    //    bind(classOf[DeleteLiftTypesFromTrainingMenuRepositoryInterface]).to(classOf[DeleteLiftTypesFromTrainingMenuRepository])
    //    bind(classOf[ListLiftTypeRepositoryInterface]).to(classOf[ListLiftTypeRepository])
    //    bind(classOf[ListMenuLiftTypeRepositoryInterface]).to(classOf[ListMenuLiftTypeRepository])
  }

}

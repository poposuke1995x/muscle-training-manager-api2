//package domain.training.service
//
//import com.google.inject.Inject
//import domain.training.entity.LiftTypeEntity
//import domain.training.lifecycle.LiftTypeRepositoryInterface
//import usecase.dto.LiftTypeDto
//
//import scala.concurrent.{ExecutionContext, Future}
//
//case class LiftTypeService @Inject()(repository: LiftTypeRepositoryInterface)(implicit ec: ExecutionContext) {
//
//  def createLiftType(): Future[LiftTypeEntity] = repository.insert()
//
//
//
//
//}

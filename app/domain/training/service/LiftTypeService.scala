package domain.training.service

import com.google.inject.Inject
import domain.training.lifecycle.repositories.LiftTypeRepositoryInterface

import scala.concurrent.ExecutionContext

case class LiftTypeService @Inject()(repository: LiftTypeRepositoryInterface)(implicit ec: ExecutionContext)

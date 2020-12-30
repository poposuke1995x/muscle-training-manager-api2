package domain.training_menu.service

import com.google.inject.Inject
import domain.training_menu.lifecycle.repositories.LiftTypeRepositoryInterface

import scala.concurrent.ExecutionContext

case class LiftTypeService @Inject()(repository: LiftTypeRepositoryInterface)(implicit ec: ExecutionContext)

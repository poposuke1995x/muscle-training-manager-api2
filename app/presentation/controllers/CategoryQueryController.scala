package presentation.controllers

import com.google.inject.Inject
import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import presentation.CirceController
import presentation.query_service.CategoryQueryServiceInterface

import scala.concurrent.ExecutionContext


class CategoryQueryController @Inject()
(controllerComponents: ControllerComponents, categoryQueryService: CategoryQueryServiceInterface)
    (implicit executionContext: ExecutionContext) extends CirceController(controllerComponents) {

  def index: Action[AnyContent] = Action.async {
    categoryQueryService.index
        .map(result => Ok(result.map(_.asJson).asJson))
  }
}
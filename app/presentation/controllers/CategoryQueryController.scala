package presentation.controllers

import com.google.inject.Inject
import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import presentation.support.CirceController
import usecase.query.CategoryQueryService

import scala.concurrent.ExecutionContext


class CategoryQueryController @Inject()
(categoryQueryService: CategoryQueryService)
    (cc: ControllerComponents)
    (implicit ec: ExecutionContext) extends CirceController(cc) {

  def index: Action[AnyContent] = Action.async {
    categoryQueryService.index.map { result => Ok(result.asJson) }
  }
}
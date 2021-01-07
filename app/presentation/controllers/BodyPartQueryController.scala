package presentation.controllers

import com.google.inject.Inject
import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import presentation.support.CirceController
import usecase.query.BodyPartQueryService

import scala.concurrent.ExecutionContext


class BodyPartQueryController @Inject()
(bodyPartQueryServiceInterface: BodyPartQueryService)
    (cc: ControllerComponents)
    (implicit ec: ExecutionContext) extends CirceController(cc) {

  def index: Action[AnyContent] = Action.async {
    bodyPartQueryServiceInterface.index.map { result => Ok(result.asJson) }
  }
}
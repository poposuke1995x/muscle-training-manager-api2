package presentation.controllers

import com.google.inject.Inject
import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import presentation.CirceController
import presentation.query_service.BodyPartQueryServiceInterface

import scala.concurrent.ExecutionContext


class BodyPartQueryController @Inject()
(bodyPartQueryServiceInterface: BodyPartQueryServiceInterface)
    (controllerComponents: ControllerComponents)
    (implicit executionContext: ExecutionContext) extends CirceController(controllerComponents) {

  def index: Action[AnyContent] = Action.async {
    bodyPartQueryServiceInterface.index
        .map(result => Ok(result.map(_.asJson).asJson))
  }
}
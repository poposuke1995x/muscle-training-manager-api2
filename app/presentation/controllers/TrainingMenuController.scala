package presentation.controllers

import app.getFirebaseUid
import com.google.inject.Inject
import domain.user.UserService
import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import play.api.mvc._
import presentation.{CirceController, TrainingMenuResponseModel}
import presentation.query_service.TrainingMenuQueryServiceInterface

import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuController @Inject()
(
    userService: UserService,
    trainingMenuQueryService: TrainingMenuQueryServiceInterface
)
    (controllerComponents: ControllerComponents)
    (implicit executionContext: ExecutionContext) extends CirceController(controllerComponents) {

  def index: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUid(request) match {
      case None => Future(Forbidden)
      case Some(uid) => userService.getUserId(uid).flatMap {
        case None => Future(InternalServerError)
        case Some(userId) =>
          trainingMenuQueryService.findByUserId(userId).map(result => Ok(result.map(TrainingMenuResponseModel.fromEntity(_).asJson).asJson))
      }
    }
  }


}

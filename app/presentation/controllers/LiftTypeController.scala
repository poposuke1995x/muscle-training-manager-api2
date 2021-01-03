package presentation.controllers

import app.getFirebaseUid
import com.google.inject.Inject
import domain.Id
import domain.user.UserService
import io.circe.syntax.EncoderOps
import io.circe.generic.auto.exportEncoder
import play.api.mvc.{Action, AnyContent, ControllerComponents, Request}
import presentation.query_service.LiftTypeQueryServiceInterface
import presentation.{CirceController, LiftTypeResponseModel}

import scala.concurrent.{ExecutionContext, Future}

class LiftTypeController @Inject()
(
    userService: UserService,
    liftTypeQueryService: LiftTypeQueryServiceInterface
)
    (controllerComponents: ControllerComponents)
    (implicit executionContext: ExecutionContext) extends CirceController(controllerComponents) {

  def index(published: Boolean): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUid(request) match {
      case None => Future(Forbidden)
      case Some(uid) => userService.getUserId(uid).flatMap {
        case None => Future(InternalServerError)
        case Some(userId) => {
          if (published) liftTypeQueryService.findPublished(userId)
          else liftTypeQueryService.findByUserId(userId)
        }.map { result => Ok(result.map(LiftTypeResponseModel.fromEntity).asJson) }
      }
    }
  }

  def show(liftTypeId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Id(liftTypeId) match {
      case None => Future.successful(BadRequest)
      case Some(value) => liftTypeQueryService.findById(value).map { result => Ok(result.map(LiftTypeResponseModel.fromEntity).asJson) }
    }


  }

}

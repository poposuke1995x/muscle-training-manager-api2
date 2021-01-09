package presentation.controllers

import com.google.inject.Inject
import domain.support.{Id, RequestError}
import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import play.api.mvc.{Action, AnyContent, ControllerComponents, Request}
import presentation.support.CirceController
import usecase.command.{CreateLiftTypeUseCase, DeleteLiftTypeUseCase, UpdateLiftTypeUseCase}
import usecase.dto.{LiftTypeCreateRequestModel, LiftTypeResponseModel, LiftTypeUpdateRequestModel}
import usecase.query.LiftTypeQueryService

import scala.concurrent.{ExecutionContext, Future}

class LiftTypeController @Inject()
(
    liftTypeQueryService: LiftTypeQueryService,
    createLiftTypeUseCase: CreateLiftTypeUseCase,
    updateLiftTypeUseCase: UpdateLiftTypeUseCase,
    deleteLiftTypeUseCase: DeleteLiftTypeUseCase
)(cc: ControllerComponents)(implicit ec: ExecutionContext) extends CirceController(cc) {


  def index(published: Boolean): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUidAction(request.headers) { uid => {
      if (published) liftTypeQueryService.findPublished(uid)
      else liftTypeQueryService.findByUserId(uid)
    }.map { result => Ok(result.map(LiftTypeResponseModel.fromEntity).asJson) }
    }
  }

  def show(liftTypeId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Id(liftTypeId) match {
      case None => Future.successful(BadRequest)
      case Some(value) =>
        liftTypeQueryService.findById(value).map { result => Ok(result.map(LiftTypeResponseModel.fromEntity).asJson) }
    }
  }

  def create: Action[LiftTypeCreateRequestModel] =
    Action.async(circe.tolerantJson[LiftTypeCreateRequestModel]) { request =>
      getFirebaseUidAction(request.headers) {
        createLiftTypeUseCase(_)(request.body).map {
          case Left(err) => InternalServerError(err.toString)
          case Right(result) => Ok(result.asJson)
        }
      }
    }

  def update(liftTypeId: Int): Action[LiftTypeUpdateRequestModel] =
    Action.async(circe.tolerantJson[LiftTypeUpdateRequestModel]) { request =>
      Id(liftTypeId) match {
        case None => Future.successful(BadRequest)
        case Some(liftTypeId) => getFirebaseUidAction(request.headers) {
          updateLiftTypeUseCase(_)(liftTypeId, request.body).map {
            case Left(_: RequestError) => BadRequest
            case Left(_) => InternalServerError
            case Right(result) => Ok(result.asJson)
          }
        }
      }
    }

  def delete(liftTypeId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUidAction(request.headers) {
      uid =>
        Id(liftTypeId) match {
          case None => Future.successful(BadRequest)
          case Some(value) => deleteLiftTypeUseCase(uid)(value).map {
            case false => InternalServerError
            case true => NoContent
          }
        }
    }
  }
}

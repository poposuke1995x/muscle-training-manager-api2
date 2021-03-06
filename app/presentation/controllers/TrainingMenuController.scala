package presentation.controllers

import com.google.inject.Inject
import domain.support.Id
import io.circe.generic.auto.exportEncoder
import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import play.api.mvc._
import presentation.support.CirceController
import usecase.command.{CreateTrainingMenuUseCase, DeleteTrainingMenuUseCase}
import usecase.dto.{LiftTypeResponseModel, TrainingMenuRequestModel, TrainingMenuResponseModel}
import usecase.query.TrainingMenuQueryService

import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuController @Inject()
(
    trainingMenuQueryService: TrainingMenuQueryService,
    createTrainingMenuUseCase: CreateTrainingMenuUseCase,
    deleteTrainingMenuUseCase: DeleteTrainingMenuUseCase
)(cc: ControllerComponents)(implicit ec: ExecutionContext) extends CirceController(cc) {

  def index: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUidAction(request.headers) { uid =>
      trainingMenuQueryService
          .findByUserId(uid)
          .map { result => Ok(result.map(TrainingMenuResponseModel.fromEntity).asJson) }
    }
  }

  def showLiftTypes(menuId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUidAction(request.headers) { uid =>
      Id(menuId) match {
        case None => Future.successful(BadRequest)
        case Some(value) => trainingMenuQueryService
            .findLiftTypes(uid)(value)
            .map { result => Ok(result.map(LiftTypeResponseModel.fromEntity).asJson) }
      }
    }
  }

  def create: Action[TrainingMenuRequestModel] = Action.async(circe.tolerantJson[TrainingMenuRequestModel]) { request =>
    getFirebaseUidAction(request.headers) { uid =>
      createTrainingMenuUseCase(uid)(request.body).map {
        case Left(err) => InternalServerError(err.toString)
        case Right(result) => Ok(result.asJson)
      }
    }


  }

  def delete(trainingMenuId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    getFirebaseUidAction(request.headers) {
      uid =>
        Id(trainingMenuId) match {
          case None => Future.successful(BadRequest)
          case Some(value) => deleteTrainingMenuUseCase(uid)(value).map {
            case false => InternalServerError
            case true => NoContent
          }
        }
    }


  }
}

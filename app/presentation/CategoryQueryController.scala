package presentation

import com.google.inject.Inject
import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder
import io.circe.syntax.EncoderOps
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import presentation.query_service.{CategoryQueryServiceInterface, CategoryResponseModel}

import scala.concurrent.ExecutionContext


class CategoryQueryController @Inject()
(controllerComponents: ControllerComponents, categoryQueryService: CategoryQueryServiceInterface)
    (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) with Circe {
  def index: Action[AnyContent] = Action.async {
    implicit val encoder: Encoder[CategoryResponseModel] = deriveEncoder
    categoryQueryService.index
        .map(_.map(_.asJson).asJson)
        .map { Ok(_) }
  }
}
package presentation

import com.google.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}


class WakeUpController @Inject()(controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  def index: Action[AnyContent] = Action(Ok)
}
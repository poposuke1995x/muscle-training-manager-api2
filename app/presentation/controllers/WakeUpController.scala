package presentation.controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}


class WakeUpController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {
  def index: Action[AnyContent] = Action(Ok)
}
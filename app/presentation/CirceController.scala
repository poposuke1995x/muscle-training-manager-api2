package presentation

import com.google.inject.Inject
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

abstract class CirceController @Inject()
(controllerComponents: ControllerComponents) extends AbstractController(controllerComponents) with Circe

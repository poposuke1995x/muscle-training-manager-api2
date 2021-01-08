package presentation.support

import app.getFirebaseUid
import com.google.inject.Inject
import domain.user.FirebaseUid
import io.circe.{Decoder, Json}
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode
import play.api.libs.circe.Circe
import play.api.mvc._
import shapeless.Lazy.apply
import usecase.dto.{LiftTypeRequestModel, TrainingMenuRequestModel}

import scala.concurrent.{ExecutionContext, Future}

abstract class CirceController @Inject()
(cc: ControllerComponents)
    (implicit ec: ExecutionContext) extends AbstractController(cc) with Circe {

  def getFirebaseUidAction(headers: Headers)(f: FirebaseUid => Future[Result]): Future[Result] =
    getFirebaseUid(headers) match {
      case None => Future(Forbidden)
      case Some(uid) => f(uid)
    }

}



import com.google.firebase.auth.{FirebaseAuth, FirebaseAuthException}
import domain.user.FirebaseUid
import play.api.mvc.RequestHeader

package object Utils {
  def getIdToken[T <: RequestHeader](rh: T): Option[String] = rh.headers.get("Authorization")

  def getFirebaseUid[T <: RequestHeader](rh: T): Option[FirebaseUid] = getIdToken(rh).flatMap(getFirebaseUid)

  private def getFirebaseUid(idToken: String): Option[FirebaseUid] =
    try FirebaseUid(FirebaseAuth.getInstance.verifyIdToken(idToken).getUid)
    catch {
      case err: FirebaseAuthException =>
        println(err)
        None
    }


}

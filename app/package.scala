import com.google.firebase.auth.{FirebaseAuth, FirebaseAuthException}
import domain.user.FirebaseUid
import play.api.mvc.Headers

package object app {
  def getIdToken(headers: Headers): Option[String] = headers.get("Authorization")

  def getFirebaseUid(headers: Headers): Option[FirebaseUid] =
    getIdToken(headers).flatMap(getFirebaseUid)

  private def getFirebaseUid(idToken: String): Option[FirebaseUid] =
    try FirebaseUid(FirebaseAuth.getInstance.verifyIdToken(idToken).getUid)
    catch {
      case err: FirebaseAuthException =>
        println(err)
        None
    }
}
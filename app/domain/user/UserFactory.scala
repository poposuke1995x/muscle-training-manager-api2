package domain.user

import domain.support.Id

case class UserFactory(id: Option[Int], name: String, firebaseUid: String)

object UserFactory {
  def apply(id: Option[Int], name: String, firebaseUid: String): Option[UserEntity] =
    UserName(name).flatMap {
      nameValue =>
        FirebaseUid(firebaseUid).flatMap {
          firebaseUidValue =>
            id.flatMap(Id(_)).map {
              idValue => UserEntity(idValue, nameValue, firebaseUidValue)
            }
        }
    }
}

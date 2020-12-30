package domain.user

import domain.Id

case class UserFactory(id: Int, name: String, firebaseUid: String)

object UserFactory {
  def apply(id: Int, name: String, firebaseUid: String): Option[UserEntity] =
    UserName(name).flatMap {
      nameValue =>
        FirebaseUid(firebaseUid).flatMap {
          firebaseUidValue =>
            Id(id).map {
              idValue => UserEntity(idValue, nameValue, firebaseUidValue)
            }
        }
    }
}

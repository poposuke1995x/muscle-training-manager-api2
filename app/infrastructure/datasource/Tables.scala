package infrastructure.datasource


import com.google.inject.Inject
import domain.training.entity.{LiftTypeEntity, TrainingMenuEntity}
import domain.user.UserEntity
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import usecase.dto.{BodyPartResponseModel, CategoryResponseModel}

import scala.concurrent.ExecutionContext

class Tables @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit ec: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._


  class UsersTable(tag: Tag) extends Table[UserModel](tag, "users") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def firebase_uid = column[String]("firebase_uid")

    def * = (id, name, firebase_uid).<>(UserModel.tupled, UserModel.unapply)
  }
  object UsersTable {
    def fromEntity(entity: UserEntity): UserModel = UserModel(
      Some(entity.id.value),
      entity.name.value,
      entity.uid.value
    )
  }

  class BodyPartsTable(tag: Tag) extends Table[BodyPartResponseModel](tag, "body_parts") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def categoryId = column[Int]("category_id")

    def * = (id, name, categoryId).<>(BodyPartResponseModel.tupled, BodyPartResponseModel.unapply)

  }

  class CategoriesTable(tag: Tag) extends Table[CategoryResponseModel](tag, "categories") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name).<>(CategoryResponseModel.tupled, CategoryResponseModel.unapply)
  }


  class LiftActionsTable(tag: Tag) extends Table[LiftActionModel](tag, "lift_actions") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def liftTypeId = column[Int]("lift_type_id")

    def trainingMenuId = column[Int]("training_menu_id")

    def lightRep = column[Int]("light_rep")

    def lightWeight = column[Int]("light_weight")

    def lightSetCount = column[Int]("light_set_count")

    def heavyRep = column[Int]("heavy_rep")

    def heavyWeight = column[Int]("heavy_weight")

    def heavySetCount = column[Int]("heavy_set_count")

    def * = (
        id,
        liftTypeId,
        trainingMenuId,
        lightRep,
        lightWeight,
        lightSetCount,
        heavyRep,
        heavyWeight,
        heavySetCount,
        ).<>(LiftActionModel.tupled, LiftActionModel.unapply)
  }


  class LiftTypesTable(tag: Tag) extends Table[LiftTypeModel](tag, "lift_types") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def referenceUrl = column[Option[String]]("reference_url")

    def description = column[Option[String]]("description")

    def userId = column[Int]("user_id")

    def defaultRep = column[Int]("default_rep")

    def defaultWeight = column[Int]("default_weight")

    def defaultSetCount = column[Int]("default_set_count")

    def shareFlag = column[Boolean]("share_flag")

    def * = (
        id,
        name,
        referenceUrl,
        description,
        userId,
        defaultRep,
        defaultWeight,
        defaultSetCount,
        shareFlag
        ).<>(LiftTypeModel.tupled, LiftTypeModel.unapply)
  }
  object LiftTypesTable {
    def fromEntity(liftTypeEntity: LiftTypeEntity): LiftTypeModel = LiftTypeModel(
      liftTypeEntity.id.map(_.value),
      liftTypeEntity.name.value,
      liftTypeEntity.referenceUrl,
      liftTypeEntity.description.map(_.value),
      liftTypeEntity.userId.value,
      liftTypeEntity.defaultAction.rep.value,
      liftTypeEntity.defaultAction.weight.value,
      liftTypeEntity.defaultAction.setCount.value,
      liftTypeEntity.shareFlag
    )
  }


  class TargetsTable(tag: Tag) extends Table[TargetModel](tag, "targets") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def liftActionId = column[Int]("lift_action_id")

    def bodyPartId = column[Int]("body_part_id")

    def isMain = column[Boolean]("is_main")

    def * = (id, liftActionId, bodyPartId, isMain).<>(TargetModel.tupled, TargetModel.unapply)
  }


  class TrainingMenuTable(tag: Tag) extends Table[TrainingMenuModel](tag, "training_menu") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def description = column[Option[String]]("description")

    def userId = column[Int]("user_id")

    def shareFlag = column[Boolean]("share_flag")

    def * = (
        id,
        name,
        description,
        userId,
        shareFlag
        ).<>(TrainingMenuModel.tupled, TrainingMenuModel.unapply)

  }
  object TrainingMenuTable {
    def fromEntity(entity: TrainingMenuEntity): TrainingMenuModel = TrainingMenuModel(
      entity.id.map(_.value),
      entity.name.value,
      entity.description.map(_.value),
      entity.userId.value,
      entity.shareFlag
    )
  }


}

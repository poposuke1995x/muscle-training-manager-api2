package domain.training_menu.lifecycle

case class LiftTypeModel(
    id: Option[Int],
    name: String,
    referenceUrl: Option[String],
    description: Option[String],
    userId: Int,
    defaultRep: Int = 0,
    defaultWeight: Int = 0,
    defaultSetCount: Int = 0,
    shareFlag: Boolean = false
)

case class TrainingActionModel()

case class TrainingMenuModel(
    id: Option[Int],
    name: String,
    description: Option[String],
    userId: Int,
    shareFlag: Boolean = false
)


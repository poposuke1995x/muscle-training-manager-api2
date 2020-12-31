package domain.training.entity

import domain.{Description, Id}

case class TrainingMenuEntity(
    id: Option[Id],
    name: TrainingMenuName,
    description: Option[Description],
    userId: Id,
    shareFlag: Boolean = false
)
case class TrainingMenuName(value: String) extends AnyVal
object TrainingMenuName {
  def apply(value: String): Option[TrainingMenuName] = Some(value).filter(validate).map(new TrainingMenuName(_))

  def validate(value: String): Boolean = value.nonEmpty && value.length <= 50
}

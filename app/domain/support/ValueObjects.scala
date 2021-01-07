package domain.support

case class Id(value: Int) extends AnyVal

object Id {
  def apply(value: Int): Option[Id] = Some(value).filter(validate).map(new Id(_))

  def validate(value: Int): Boolean = value > 0
}

case class Description(value: String) extends AnyVal

object Description {
  def apply(value: String): Option[Description] = Some(value).filter(validate).map(new Description(_))

  def validate(value: String): Boolean = value.nonEmpty && value.length <= 200
}

case class NaturalNumberAndZero(value: Int) extends AnyVal

object NaturalNumberAndZero {
  def apply(value: Int): Option[NaturalNumberAndZero] = Some(value).filter(validate).map(new NaturalNumberAndZero(_))

  def validate(value: Int): Boolean = value >= 0
}

case class Action(
    rep: NaturalNumberAndZero,
    weight: NaturalNumberAndZero,
    setCount: NaturalNumberAndZero
)

object Action {
  def apply(rep: Int, weight: Int, setCount: Int): Option[Action] =
    NaturalNumberAndZero(rep).flatMap(rep => NaturalNumberAndZero(weight).flatMap {
      weight =>
        NaturalNumberAndZero(setCount).map {
          setCount => new Action(rep, weight, setCount)
        }
    })
}

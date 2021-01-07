package domain.support

trait Error
final case class EntityNotFoundError() extends Error
final case class DbError() extends Error
final case class RequestError() extends Error
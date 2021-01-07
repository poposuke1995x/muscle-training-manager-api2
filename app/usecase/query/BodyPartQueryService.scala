package usecase.query

import usecase.dto.BodyPartResponseModel

import scala.concurrent.Future

trait BodyPartQueryService {
  def index: Future[List[BodyPartResponseModel]]

  def findById(id: Int): Future[Option[BodyPartResponseModel]]
}

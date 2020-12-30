package presentation.query_service

import scala.concurrent.Future

trait BodyPartQueryServiceInterface {
//  def index: Future[List[BodyPartResponseModel]]
  def index: Future[List[BodyPartResponseModel]]

//  def findById(id: Int): Future[Option[BodyPartResponseModel]]
  def findById(id: Int): Future[Option[BodyPartResponseModel]]
}

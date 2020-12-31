package presentation.query_service

import presentation.BodyPartResponseModel

import scala.concurrent.Future

trait BodyPartQueryServiceInterface {
//  def index: Future[List[BodyPartResponseModel]]
  def index: Future[List[BodyPartResponseModel]]

//  def findById(id: Int): Future[Option[BodyPartResponseModel]]
  def findById(id: Int): Future[Option[BodyPartResponseModel]]
}

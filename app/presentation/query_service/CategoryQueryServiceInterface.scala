package presentation.query_service

import scala.concurrent.Future

trait CategoryQueryServiceInterface {
  def index: Future[List[CategoryResponseModel]]
  def findById(id: Int): Future[Option[CategoryResponseModel]]
}

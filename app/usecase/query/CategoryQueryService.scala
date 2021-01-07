package usecase.query

import usecase.dto.CategoryResponseModel

import scala.concurrent.Future

trait CategoryQueryService{
  def index: Future[List[CategoryResponseModel]]
  def findById(id: Int): Future[Option[CategoryResponseModel]]
}

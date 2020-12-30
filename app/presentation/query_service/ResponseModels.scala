package presentation.query_service

case class BodyPartResponseModel(id: Option[Int], name: String, categoryId: Int)
case class CategoryResponseModel(id: Option[Int], name: String)
//object BodyPartResponseModel{
//  //  implicit val jsonConfig: Configuration = Configuration.default.withSnakeCaseMemberNames
//  //  implicit val userNameDecoder: Decoder[BodyPartResponseModel] = deriveConfiguredDecoder
//  //  implicit val userNameEncoder: Encoder[BodyPartResponseModel] = deriveConfiguredEncoder
//  implicit val encoder: Encoder[BodyPartResponseModel] = deriveEncoder
//  implicit val decoder: Decoder[BodyPartResponseModel] = deriveDecoder
//}
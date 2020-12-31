import com.google.inject.Inject
import module.AuthFilter
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter

class Filters @Inject()(authFilter: AuthFilter, corsFilter: CORSFilter) extends HttpFilters {
  val filters = Seq(
//    authFilter,
    corsFilter
  )
}

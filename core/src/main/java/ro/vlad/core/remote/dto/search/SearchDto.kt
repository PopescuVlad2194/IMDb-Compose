package ro.vlad.core.remote.dto.search

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ro.vlad.core.remote.dto.search.Result

@Keep
data class SearchDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("expression")
    val expression: String?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("searchType")
    val searchType: String?
)
package ro.vlad.core.remote.dto.top

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ro.vlad.core.remote.dto.top.ItemTop

@Keep
data class TopMoviesDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("items")
    val items: List<ItemTop>?
)
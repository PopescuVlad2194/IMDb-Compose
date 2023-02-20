package ro.vlad.core.remote.dto.most_popular

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ro.vlad.core.remote.dto.most_popular.ItemPopular

@Keep
data class MostPopularDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("items")
    val items: List<ItemPopular>?
)
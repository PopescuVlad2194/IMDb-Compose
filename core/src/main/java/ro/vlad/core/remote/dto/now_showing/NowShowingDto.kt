package ro.vlad.core.remote.dto.now_showing

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ro.vlad.core.remote.dto.now_showing.ItemNowShowing

@Keep
data class NowShowingDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("items")
    val items: List<ItemNowShowing>?
)
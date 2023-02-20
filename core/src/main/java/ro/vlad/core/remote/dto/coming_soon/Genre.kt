package ro.vlad.core.remote.dto.coming_soon

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Genre(
    @SerializedName("key")
    val key: String?,
    @SerializedName("value")
    val value: String?
)
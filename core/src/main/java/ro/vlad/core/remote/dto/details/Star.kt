package ro.vlad.core.remote.dto.details

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Star(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
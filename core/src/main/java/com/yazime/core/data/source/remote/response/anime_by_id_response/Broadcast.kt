package com.yazime.core.data.source.remote.response.anime_by_id_response


import com.google.gson.annotations.SerializedName

data class Broadcast(
    @SerializedName("day")
    val day: String?,
    @SerializedName("string")
    val string: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("timezone")
    val timezone: String?
)
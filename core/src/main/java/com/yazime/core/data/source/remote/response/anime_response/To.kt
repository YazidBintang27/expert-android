package com.yazime.core.data.source.remote.response.anime_response


import com.google.gson.annotations.SerializedName

data class To(
    @SerializedName("day")
    val day: Int?,
    @SerializedName("month")
    val month: Int?,
    @SerializedName("year")
    val year: Int?
)
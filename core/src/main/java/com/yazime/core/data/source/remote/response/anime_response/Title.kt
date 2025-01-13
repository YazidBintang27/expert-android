package com.yazime.core.data.source.remote.response.anime_response


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)
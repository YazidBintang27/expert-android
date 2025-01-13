package com.yazime.core.data.source.remote.response.anime_by_id_response


import com.google.gson.annotations.SerializedName

data class Streaming(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)
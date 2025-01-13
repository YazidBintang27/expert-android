package com.yazime.core.data.source.remote.response.anime_response


import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("pagination")
    val pagination: Pagination?
)
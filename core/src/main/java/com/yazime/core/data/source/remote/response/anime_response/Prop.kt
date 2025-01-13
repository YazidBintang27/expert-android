package com.yazime.core.data.source.remote.response.anime_response


import com.google.gson.annotations.SerializedName

data class Prop(
    @SerializedName("from")
    val from: From?,
    @SerializedName("to")
    val to: To?
)
package com.yazime.core.data.source.remote.response.anime_by_id_response


import com.google.gson.annotations.SerializedName

data class Aired(
    @SerializedName("from")
    val from: String?,
    @SerializedName("prop")
    val prop: Prop?,
    @SerializedName("string")
    val string: String?,
    @SerializedName("to")
    val to: String?
)
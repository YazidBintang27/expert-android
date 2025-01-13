package com.yazime.core.data.source.remote.response.anime_by_id_response


import com.google.gson.annotations.SerializedName

data class Relation(
    @SerializedName("entry")
    val entry: List<Entry?>?,
    @SerializedName("relation")
    val relation: String?
)
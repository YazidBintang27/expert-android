package com.yazime.core.data.source.remote.response.anime_by_id_response


import com.google.gson.annotations.SerializedName

data class Theme(
    @SerializedName("endings")
    val endings: List<String?>?,
    @SerializedName("openings")
    val openings: List<String?>?
)
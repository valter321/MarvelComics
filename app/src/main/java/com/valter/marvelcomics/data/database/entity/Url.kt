package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Url(
    val type: String,
    val url: String
)
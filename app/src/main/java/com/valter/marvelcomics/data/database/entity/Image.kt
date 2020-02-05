package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val extension: String,
    val path: String
)
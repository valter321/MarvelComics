package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicSummary(
    val name: String,
    val resourceURI: String
)
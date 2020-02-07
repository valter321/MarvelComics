package com.valter.marvelcomics.data.database.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicImage(
    val extension: String,
    val path: String
)
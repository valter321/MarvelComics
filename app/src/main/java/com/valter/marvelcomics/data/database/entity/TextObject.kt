package com.valter.marvelcomics.data.database.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextObject(
    val language: String,
    val text: String,
    val type: String
)
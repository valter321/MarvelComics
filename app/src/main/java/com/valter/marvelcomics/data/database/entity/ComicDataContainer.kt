package com.valter.marvelcomics.data.database.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicDataContainer(
        val count: String,
        val limit: String,
        val offset: String,
        val results: List<Comic>,
        val total: String
)
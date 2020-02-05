package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.JsonClass
import com.valter.marvelcomics.data.database.entity.Comic

@JsonClass(generateAdapter = true)
data class ComicDataContainer(
        val count: String,
        val limit: String,
        val offset: String,
        val comics: List<Comic>,
        val total: String
)
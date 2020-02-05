package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.JsonClass
import com.valter.marvelcomics.data.database.entity.ComicDataContainer

@JsonClass(generateAdapter = true)
data class ComicDataWrapper(
        val attributionHTML: String,
        val attributionText: String,
        val code: String,
        val copyright: String,
        val comicDataContainer: ComicDataContainer,
        val etag: String,
        val status: String
)
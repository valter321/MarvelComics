package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryList(
        val available: String,
        val collectionURI: String,
        val items: List<StorySummary>,
        val returned: String
)
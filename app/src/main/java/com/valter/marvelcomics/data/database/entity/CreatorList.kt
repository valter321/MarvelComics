package com.valter.marvelcomics.data.database.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatorList(
        val available: String,
        val collectionURI: String,
        val items: List<CreatorSummary>,
        val returned: String
)
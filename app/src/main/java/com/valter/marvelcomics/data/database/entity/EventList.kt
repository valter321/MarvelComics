package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventList(
        val available: String,
        val collectionURI: String,
        val items: List<EventSummary>,
        val returned: String
)
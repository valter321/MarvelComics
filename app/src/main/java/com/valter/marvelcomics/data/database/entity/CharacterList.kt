package com.valter.marvelcomics.data.database.entity


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterList(
        val available: String,
        val collectionURI: String,
        val items: List<CharacterSummary>,
        val returned: String
)
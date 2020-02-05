package com.valter.marvelcomics.data.database.entity


import androidx.room.Entity
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Comic(
        val characters: CharacterList,
        val collectedIssues: List<ComicSummary>,
        val collections: List<ComicSummary>,
        val creators: CreatorList,
        val dates: List<ComicDate>,
        val description: String,
        val diamondCode: String,
        val digitalId: String,
        val ean: String,
        val events: EventList,
        val format: String,
        val id: String,
        val images: List<Image>,
        val isbn: String,
        val issn: String,
        val issueNumber: String,
        val modified: String,
        val pageCount: String,
        val prices: List<ComicPrice>,
        val resourceURI: String,
        val series: SeriesSummary,
        val stories: StoryList,
        val textObjects: List<TextObject>,
        val thumbnail: Image,
        val title: String,
        val upc: String,
        val urls: List<Url>,
        val variantDescription: String,
        val variants: List<ComicSummary>
)
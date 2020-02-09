package com.valter.marvelcomics.data.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Comic(
        val creators: CreatorList,
        val description: String?,
        val digitalId: String,
        val format: String,
        @PrimaryKey
        val id: String,
        val images: List<ComicImage>?,
        val issueNumber: String,
        val pageCount: String,
        val resourceURI: String,
        val thumbnail: ComicImage,
        val title: String,
        val variantDescription: String,
        val variants: List<ComicSummary>?,
        val series: SeriesSummary
)
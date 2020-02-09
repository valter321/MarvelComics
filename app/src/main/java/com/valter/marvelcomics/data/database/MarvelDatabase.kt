package com.valter.marvelcomics.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valter.marvelcomics.data.converters.*
import com.valter.marvelcomics.data.database.entity.Comic

@Database(
        entities = [Comic::class],
        version = 1
)
@TypeConverters(
        ImageTypeConverter::class,
        CreatorListTypeConverter::class,
        ImageListTypeConverter::class,
        CreatorSummaryListTypeConverter::class,
        ComicSummaryListTypeConverter::class,
        SeriesSummaryTypeConverter::class
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
}
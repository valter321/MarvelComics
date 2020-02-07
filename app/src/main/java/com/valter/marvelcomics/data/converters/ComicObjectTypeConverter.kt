package com.valter.marvelcomics.data.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class ComicObjectTypeConverter<T>(private val tClass: Class<T>) : KoinComponent {

    private val moshi by inject<Moshi>()

    @TypeConverter
    fun fromObject(comicObject: T?): String? {
        if (comicObject == null) {
            return null
        }

        val adapter: JsonAdapter<T> = moshi.adapter(tClass)

        return adapter.toJson(comicObject)
    }

    @TypeConverter
    fun toObject(source: String?): T? {
        if (source.isNullOrBlank()) {
            return null
        }

        val adapter: JsonAdapter<T> = moshi.adapter(tClass)

        return adapter.fromJson(source)
    }
}
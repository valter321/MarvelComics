package com.valter.marvelcomics.data.repository

import com.valter.marvelcomics.data.database.ComicDao
import com.valter.marvelcomics.data.database.MarvelDatabase
import com.valter.marvelcomics.data.database.entity.Comic

class MarvelRepositoryImpl(
        private val comicDao: ComicDao,
        private val marvelDatabase: MarvelDatabase
) : MarvelRepository {
    override suspend fun getComic(searchQuery: String?, page: String, isFirstPage: Boolean): List<Comic> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun persistData(comics: List<Comic>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
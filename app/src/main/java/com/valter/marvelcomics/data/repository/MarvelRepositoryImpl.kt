package com.valter.marvelcomics.data.repository

import android.content.Context
import com.valter.marvelcomics.data.database.ComicDao
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.network.MarvelService
import com.valter.marvelcomics.ui.main.ComicLoadData
import com.valter.marvelcomics.utils.isConnectedToNetwork

class MarvelRepositoryImpl(
        private val comicDao: ComicDao,
        private val marvelService: MarvelService,
        context: Context
) : MarvelRepository {

    private val appContext = context.applicationContext

    override suspend fun getComics(page: Int) = if (appContext.isConnectedToNetwork()) {
        fetchPeopleInfoFromNetwork(page)
    } else {
        fetchPeopleInfoFromDatabase()
    }

    private suspend fun fetchPeopleInfoFromNetwork(page: Int) = marvelService.fetchAllComics(page).data.results.toLoadData(page + 1).also {
            persistData(it.comics)
        }

    private suspend fun fetchPeopleInfoFromDatabase() = comicDao.getAllComic().toLoadData(null)

    override suspend fun persistData(comics: List<Comic>) = comicDao.insert(comics)

    private fun List<Comic>.toLoadData(nextPage: Int?) = ComicLoadData(this, nextPage)

}
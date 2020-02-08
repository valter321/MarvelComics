package com.valter.marvelcomics.data.repository

import android.content.Context
import com.valter.marvelcomics.data.database.ComicDao
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.network.MarvelService
import com.valter.marvelcomics.ui.list.ComicLoadData
import com.valter.marvelcomics.utils.isConnectedToNetwork

class MarvelRepositoryImpl(
        private val comicDao: ComicDao,
        private val marvelService: MarvelService,
        context: Context
) : MarvelRepository {

    private val appContext = context.applicationContext

    override suspend fun getComics(searchQuery: String?, page: String) = if (searchQuery.isNullOrEmpty()) {
        if (appContext.isConnectedToNetwork()) {
            fetchComicsInfoFromNetwork(page.toInt())
        } else {
            fetchComicInfoFromDatabase()
        }
    } else {
        filterComics(searchQuery)
    }

    private suspend fun fetchComicsInfoFromNetwork(page: Int) = marvelService.fetchAllComics(page).data.results.toLoadData((page + 1).toString()).also {
            persistData(it.comics)
        }

    private suspend fun filterComics(searchQuery: String) = comicDao.getComic("$searchQuery%").toLoadData(null)

    private suspend fun fetchComicInfoFromDatabase() = comicDao.getAllComic().toLoadData(null)

    override suspend fun persistData(comics: List<Comic>) = comicDao.insert(comics)

    private fun List<Comic>.toLoadData(nextPage: String?) = ComicLoadData(this, nextPage)

    override suspend fun fetchComic(comicId: String) = marvelService.fetchComic(comicId.toInt()).data.results[0]
}
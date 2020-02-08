package com.valter.marvelcomics.data.repository

import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.ui.list.ComicLoadData

interface MarvelRepository {

    suspend fun getComics(searchQuery: String? = null, page: String): ComicLoadData
    suspend fun fetchComic(comicId: String) : Comic
    suspend fun persistData(comics: List<Comic>)
}
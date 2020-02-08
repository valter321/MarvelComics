package com.valter.marvelcomics.data.repository

import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.ui.main.ComicLoadData
import com.valter.marvelcomics.utils.Outcome

interface MarvelRepository {

    suspend fun getComics(searchQuery: String? = null, page: String): ComicLoadData
    suspend fun persistData(comics: List<Comic>)
}
package com.valter.marvelcomics.data.repository

import com.valter.marvelcomics.data.database.entity.Comic

interface MarvelRepository {

    suspend fun getComic(searchQuery: String? = null, page: String, isFirstPage: Boolean): List<Comic>
    suspend fun persistData(comics: List<Comic>)
}
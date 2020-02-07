package com.valter.marvelcomics.data.network

import com.valter.marvelcomics.data.database.entity.ComicDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    suspend fun fetchAllComics(
            @Query("offset") offset: Int
    ) : ComicDataWrapper

    @GET("comics")
    suspend fun fetchComic(
            @Query("comicId") comicId: String
    ) : ComicDataWrapper
}
package com.valter.marvelcomics.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    suspend fun fetchAllComics()

    @GET("comics")
    suspend fun fetchComic(
            @Query("comicId")
            comicId: String
    )
}
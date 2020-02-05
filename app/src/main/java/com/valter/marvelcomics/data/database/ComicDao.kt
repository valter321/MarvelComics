package com.valter.marvelcomics.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valter.marvelcomics.data.database.entity.Comic

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comics: List<Comic>)

    @Query("SELECT * FROM marvel")
    suspend fun getAllComic() : List<Comic>

    @Query("SELECT * FROM marvel WHERE title LIKE :title")
    suspend fun getComic(title: String) : List<Comic>
}

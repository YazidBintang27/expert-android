package com.yazime.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yazime.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
   @Insert
   fun insertFavouriteAnime(anime: List<AnimeEntity>)

   @Update
   fun updateFavouriteAnime(anime: AnimeEntity)

   @Query("SELECT * FROM anime")
   fun getAllAnime(): Flow<List<AnimeEntity>>

   @Query("SELECT * FROM anime WHERE isFavourite = 1")
   fun getFavouriteAnime(): Flow<List<AnimeEntity>>

   @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
   fun getAnimeById(id: Int): Flow<AnimeEntity>
}
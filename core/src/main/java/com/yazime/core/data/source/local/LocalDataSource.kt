package com.yazime.core.data.source.local

import com.yazime.core.data.source.local.entity.AnimeEntity
import com.yazime.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(private val animeDao: AnimeDao) {
   fun getAllAnime(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

   fun getFavouriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavouriteAnime()

   suspend fun insertAnime(animeList: List<AnimeEntity>) {
      withContext(Dispatchers.IO) {
         animeDao.insertFavouriteAnime(animeList)
      }
   }

   fun setFavouriteAnime(anime: AnimeEntity, newState: Boolean) {
      anime.isFavourite = newState
      animeDao.updateFavouriteAnime(anime)
   }

   fun getAnimeById(id: Int): Flow<AnimeEntity> {
      return animeDao.getAnimeById(id)
   }
}
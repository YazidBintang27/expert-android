package com.yazime.core.domain.repository

import com.yazime.core.data.source.remote.Resource
import com.yazime.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
   fun getAllAnime(): Flow<Resource<List<Anime>>>

   fun getAnimeById(id: Int): Flow<Resource<Anime>>

   fun getFavouriteAnime(): Flow<List<Anime>>

   suspend fun setFavouriteAnime(anime: Anime, status: Boolean)

   fun searchAnime(query: String): Flow<Resource<List<Anime>>>
}
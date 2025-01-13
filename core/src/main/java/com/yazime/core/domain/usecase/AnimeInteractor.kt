package com.yazime.core.domain.usecase

import com.yazime.core.data.source.remote.Resource
import com.yazime.core.domain.model.Anime
import com.yazime.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor (private val animeRepository: AnimeRepository): AnimeUseCase {
   override fun getAllAnime(): Flow<Resource<List<Anime>>> {
      return animeRepository.getAllAnime()
   }

   override fun getAnimeById(id: Int): Flow<Resource<Anime>> {
      return animeRepository.getAnimeById(id)
   }

   override fun getFavouriteAnime(): Flow<List<Anime>> {
      return animeRepository.getFavouriteAnime()
   }

   override suspend fun setFavouriteAnime(anime: Anime, status: Boolean) {
      return animeRepository.setFavouriteAnime(anime, status)
   }

   override fun searchAnime(query: String): Flow<Resource<List<Anime>>> {
      return animeRepository.searchAnime(query)
   }
}
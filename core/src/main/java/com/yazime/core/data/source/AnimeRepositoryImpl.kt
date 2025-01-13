package com.yazime.core.data.source

import com.yazime.core.data.source.local.LocalDataSource
import com.yazime.core.data.source.remote.NetworkBoundSource
import com.yazime.core.data.source.remote.RemoteDataSource
import com.yazime.core.data.source.remote.Resource
import com.yazime.core.data.source.remote.network.ApiResponse
import com.yazime.core.data.source.remote.response.anime_response.Data
import com.yazime.core.domain.model.Anime
import com.yazime.core.domain.repository.AnimeRepository
import com.yazime.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl(
   private val localDataSource: LocalDataSource,
   private val remoteDataSource: RemoteDataSource
): AnimeRepository {
   override fun getAllAnime(): Flow<Resource<List<Anime>>> =
      object : NetworkBoundSource<List<Anime>, List<Data>>() {

         override suspend fun loadFromDB(): Flow<List<Anime>> {
            return localDataSource.getAllAnime().map {
               DataMapper.mapEntitiesToDomain(it)
            }
         }

         override fun shouldFetch(data: List<Anime>?): Boolean {
            return data.isNullOrEmpty()
         }

         override suspend fun createCall(): Flow<ApiResponse<List<Data>>> {
            return remoteDataSource.getAllAnime()
         }

         override suspend fun saveCallResult(data: List<Data>) {
            val animeList = DataMapper.mapResponseToEntities(data)
            localDataSource.insertAnime(animeList)
         }
      }.asFlow()

   override fun getAnimeById(id: Int): Flow<Resource<Anime>> {
      return remoteDataSource.getAnimeById(id).map { response ->
         when (response) {
            is ApiResponse.Success -> {
               val anime = DataMapper.mapResponseByIdToDomain(response.data)
               Resource.Success(anime)
            }
            is ApiResponse.Empty -> {
               Resource.Error("")
            }
            is ApiResponse.Error -> {
               Resource.Error(response.errorMessage)
            }
         }
      }
   }

   override fun getFavouriteAnime(): Flow<List<Anime>> {
      return localDataSource.getFavouriteAnime().map {
         DataMapper.mapEntitiesToDomain(it)
      }
   }

   override suspend fun setFavouriteAnime(anime: Anime, status: Boolean) {
      withContext(Dispatchers.IO) {
         val animeEntity = DataMapper.mapDomainToEntities(anime)
         localDataSource.setFavouriteAnime(animeEntity, status)
      }
   }

   override fun searchAnime(query: String): Flow<Resource<List<Anime>>> {
      return remoteDataSource.searchAnime(query).map { response ->
         when (response) {
            is ApiResponse.Success -> {
               val anime = DataMapper.mapResponseSearchToDomain(response.data)
               Resource.Success(anime)
            }
            is ApiResponse.Empty -> Resource.Error("")
            is ApiResponse.Error -> Resource.Error(response.errorMessage)
         }
      }
   }
}
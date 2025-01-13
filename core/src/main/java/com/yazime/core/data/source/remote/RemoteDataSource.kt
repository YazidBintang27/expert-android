package com.yazime.core.data.source.remote

import android.util.Log
import com.yazime.core.data.source.remote.network.ApiResponse
import com.yazime.core.data.source.remote.network.ApiService
import com.yazime.core.data.source.remote.response.anime_by_id_response.AnimeByIdResponse
import com.yazime.core.data.source.remote.response.anime_response.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {
   fun getAllAnime(): Flow<ApiResponse<List<Data>>> {
      return flow {
         val response = apiService.getAllAnime()
         val filteredData = response.data
         if (!filteredData.isNullOrEmpty()) {
            Log.d("RemoteDataSource", "Check remote data: $filteredData")
            emit(ApiResponse.Success(filteredData))
         } else {
            emit(ApiResponse.Empty)
         }
      }
   }

   fun getAnimeById(id: Int): Flow<ApiResponse<AnimeByIdResponse>> {
      return flow {
         try {
            val response = apiService.getAnimeById(id)
            if (response.data != null) {
               emit(ApiResponse.Success(response))
            } else {
               emit(ApiResponse.Empty)
            }
         } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "Unknown error"))
         }
      }
   }

   fun searchAnime(query: String): Flow<ApiResponse<List<Data>>> {
      return flow {
         try {
            val response = apiService.searchAnime(query = query)
            val filteredData = response.data
            if (!filteredData.isNullOrEmpty()) {
               emit(ApiResponse.Success(filteredData))
            } else {
               emit(ApiResponse.Empty)
            }
         } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "Unknown error"))
         }
      }
   }
}
package com.yazime.yazimeapp.presentation.anime_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazime.core.data.source.remote.Resource
import com.yazime.core.domain.model.Anime
import com.yazime.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {

   private var _animeData = MutableStateFlow<Resource<Anime>?>(null)
   val animeData: StateFlow<Resource<Anime>?> = _animeData

   val animeFavourite = animeUseCase.getFavouriteAnime()

   fun getAnimeDetail(id: Int) {
      Log.d(TAG, "Fetching anime detail for ID: $id")
      viewModelScope.launch {
         animeUseCase.getAnimeById(id).collect { resource ->
            Log.d(TAG, "Received data: $resource")
            _animeData.value = resource
         }
      }
   }

   fun setAnimeFavourite(anime: Anime, status: Boolean) {
      viewModelScope.launch(Dispatchers.IO) {
         animeUseCase.setFavouriteAnime(anime, status)
      }
   }

   companion object {
      const val TAG = "DetailViewModel"
   }
}
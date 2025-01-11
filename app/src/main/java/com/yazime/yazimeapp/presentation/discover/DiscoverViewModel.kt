package com.yazime.yazimeapp.presentation.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yazime.core.data.source.remote.Resource
import com.yazime.core.domain.model.Anime
import com.yazime.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.flow.Flow

class DiscoverViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {

   fun searchAnime(query: String): Flow<Resource<List<Anime>>> {
      return animeUseCase.searchAnime(query)
   }
}
package com.yazime.yazimeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yazime.core.domain.usecase.AnimeUseCase

class HomeViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {
   val animeList = animeUseCase.getAllAnime()
}
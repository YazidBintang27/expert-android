package com.yazime.yazimeapp.presentation.home

import androidx.lifecycle.ViewModel
import com.yazime.core.domain.usecase.AnimeUseCase

class HomeViewModel (animeUseCase: AnimeUseCase): ViewModel() {
   val animeList = animeUseCase.getAllAnime()
}
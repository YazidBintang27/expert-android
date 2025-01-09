package com.yazime.favourite.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yazime.core.domain.usecase.AnimeUseCase

class FavouriteViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {
   val favouriteAnime = animeUseCase.getFavouriteAnime()
}
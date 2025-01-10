package com.yazime.yazimeapp.favourite.presentation

import androidx.lifecycle.ViewModel
import com.yazime.core.domain.usecase.AnimeUseCase

class FavouriteViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {
   val favouriteAnime = animeUseCase.getFavouriteAnime()
}
package com.yazime.favourite.di

import com.yazime.favourite.presentation.favourite.FavouriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
   viewModel { FavouriteViewModel(get()) }
}
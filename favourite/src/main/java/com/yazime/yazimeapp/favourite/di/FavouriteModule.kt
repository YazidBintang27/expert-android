package com.yazime.yazimeapp.favourite.di

import com.yazime.yazimeapp.favourite.presentation.FavouriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
   viewModel { FavouriteViewModel(get()) }
}
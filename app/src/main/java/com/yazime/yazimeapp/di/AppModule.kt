package com.yazime.yazimeapp.di

import com.yazime.yazimeapp.presentation.anime_detail.AnimeDetailViewModel
import com.yazime.yazimeapp.presentation.discover.DiscoverViewModel
import com.yazime.yazimeapp.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
   viewModel { HomeViewModel(get()) }
   viewModel { AnimeDetailViewModel(get()) }
   viewModel { DiscoverViewModel(get()) }
}
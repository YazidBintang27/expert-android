package com.yazime.favourite.presentation.favourite

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.yazime.core.ui.AnimeAdapter
import com.yazime.favourite.R
import com.yazime.favourite.databinding.FragmentFavouriteBinding
import com.yazime.favourite.di.favouriteModule
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavouriteFragment : Fragment() {

   private var _binding: FragmentFavouriteBinding? = null
   private val binding get() = _binding!!
   private lateinit var navController: NavController
   private val favouriteViewModel: FavouriteViewModel by viewModel()
   private val animeAdapter: AnimeAdapter = AnimeAdapter()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      loadKoinModules(favouriteModule)
      navController = Navigation.findNavController(view)
      Log.d("FavouriteFrakmen", "TES AKSES")
      binding.toolbar.setNavigationOnClickListener { navController.navigateUp() }
      observeFavouriteAnime()
   }

   private fun observeFavouriteAnime() {
      viewLifecycleOwner.lifecycleScope.launch {
         favouriteViewModel.favouriteAnime.collect { anime ->
            binding.rvFavourite.apply {
               adapter = animeAdapter
               layoutManager = GridLayoutManager(context, 3)
               setHasFixedSize(true)
               animeAdapter.setData(anime)
            }
         }
      }
      animeAdapter.setOnItemClickCallback(object: AnimeAdapter.OnItemClickCallback {
         override fun onItemClicked(id: Int) {
            getDetailAnime(id)
         }
      })
   }

   private fun getDetailAnime(id: Int) {

   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}
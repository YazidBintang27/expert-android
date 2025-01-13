package com.yazime.yazimeapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.yazime.core.data.source.remote.Resource
import com.yazime.core.ui.AnimeAdapter
import com.yazime.yazimeapp.R
import com.yazime.yazimeapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

   private var _binding: FragmentHomeBinding? = null
   private val binding get() = _binding!!
   private lateinit var navController: NavController
   private val homeViewModel: HomeViewModel by viewModel()
   private val animeAdapter: AnimeAdapter = AnimeAdapter()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      observeMovieAnime()
      getAnimeDetail()
      navigateToFavourite()
   }

   private fun observeMovieAnime() {
      viewLifecycleOwner.lifecycleScope.launch {
         homeViewModel.animeList.collect { anime ->
            anime.let {
               when (anime) {
                  is Resource.Loading -> {
                     with(binding) {
                        lottieLoading.visibility = View.VISIBLE
                        lottieLoading.playAnimation()
                        rvAnimeMovies.visibility = View.GONE
                        cardImage.visibility = View.GONE
                     }
                  }
                  is Resource.Success -> {
                     with(binding) {
                        Glide.with(ivTopAnime)
                           .load(anime.data?.get(0)?.images)
                           .error(com.yazime.core.R.drawable.noimage)
                           .into(ivTopAnime)

                        rvAnimeMovies.apply {
                           adapter = animeAdapter
                           layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                           setHasFixedSize(true)
                           animeAdapter.setData(anime.data!!)
                        }

                        lottieLoading.visibility = View.GONE
                        lottieLoading.cancelAnimation()
                        rvAnimeMovies.visibility = View.VISIBLE
                        cardImage.visibility = View.VISIBLE
                     }
                  }
                  is Resource.Error -> {
                     with(binding) {
                        lottieLoading.visibility = View.GONE
                        lottieLoading.cancelAnimation()
                        rvAnimeMovies.visibility = View.VISIBLE
                        cardImage.visibility = View.VISIBLE
                     }
                  }
               }
            }
         }
      }
   }

   private fun navigateToFavourite() {
      binding.icFavourite.setOnClickListener {
         val splitInstallManager = SplitInstallManagerFactory.create(requireContext())

         if (splitInstallManager.installedModules.contains("favourite")) {
            Log.d(TAG, "Module already installed")
            navController.navigate(R.id.action_homeFragment_to_favourite)
            return@setOnClickListener
         }

         val request = SplitInstallRequest.newBuilder()
            .addModule("favourite")
            .build()

         splitInstallManager.startInstall(request)
            .addOnSuccessListener {
               Log.d(TAG, "Module installed successfully")
               navController.navigate(R.id.action_homeFragment_to_favourite)
            }
            .addOnFailureListener { exception ->
               Log.e(TAG, "Install failed: ${exception.message}")
               try {
                  navController.navigate(R.id.action_homeFragment_to_favourite)
               } catch (e: Exception) {
                  Log.e(TAG, "Navigation failed: ${e.message}")
               }
            }
      }
   }

   private fun getAnimeDetail() {
      animeAdapter.setOnItemClickCallback(object: AnimeAdapter.OnItemClickCallback {
         override fun onItemClicked(id: Int) {
            val action = HomeFragmentDirections.actionHomeFragmentToAnimeDetailFragment()
            action.id = id
            Log.d(TAG, "Id: $id")
            navController.navigate(action)
         }
      })
   }

   override fun onDestroyView() {
      super.onDestroyView()
      animeAdapter.setOnItemClickCallback(null)
      binding.rvAnimeMovies.adapter = null
      _binding = null
   }

   companion object {
      const val TAG = "HomeFragment"
   }
}
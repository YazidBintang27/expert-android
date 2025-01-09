package com.yazime.yazimeapp.presentation.discover

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.yazime.core.data.source.remote.Resource
import com.yazime.core.ui.AnimeAdapter
import com.yazime.yazimeapp.R
import com.yazime.yazimeapp.databinding.FragmentDiscoverBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment() {

   private var _binding: FragmentDiscoverBinding? = null
   private val binding get() = _binding!!
   private val discoverViewModel: DiscoverViewModel by viewModel()
   private lateinit var navController: NavController
   private val animeAdapter: AnimeAdapter = AnimeAdapter()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      observeAnime()
      getAnimeDetail()
   }

   private fun observeAnime() {
      binding.searchView.setOnQueryTextListener(object: OnQueryTextListener {
         override fun onQueryTextSubmit(query: String?): Boolean {
            return false
         }

         override fun onQueryTextChange(newText: String?): Boolean {
            viewLifecycleOwner.lifecycleScope.launch {
               discoverViewModel.searchAnime(newText!!).collect { anime ->
                  anime.let {
                     when (anime) {
                        is Resource.Loading -> {
                           with(binding) {
                              lottieLoading.visibility = View.VISIBLE
                              lottieLoading.playAnimation()
                           }
                        }
                        is Resource.Success -> {
                           with(binding) {
                              lottieLoading.cancelAnimation()
                              tvResults.visibility = View.VISIBLE

                              rvSearchResult.apply {
                                 adapter = animeAdapter
                                 layoutManager = GridLayoutManager(context, 3)
                                 setHasFixedSize(true)
                                 animeAdapter.setData(anime.data!!)
                              }
                           }
                        }
                        is Resource.Error -> {
                           with(binding) {
                              lottieLoading.cancelAnimation()
                              tvResults.visibility = View.VISIBLE
                           }
                        }
                     }
                  }
               }
            }
            return true
         }
      })
   }

   private fun getAnimeDetail() {
      animeAdapter.setOnItemClickCallback(object: AnimeAdapter.OnItemClickCallback {
         override fun onItemClicked(id: Int) {
            val action = DiscoverFragmentDirections.actionDiscoverFragmentToAnimeDetailFragment()
            action.id = id
            Log.d(TAG, "Id: $id")
            Toast.makeText(requireContext(), "Not available for now", Toast.LENGTH_SHORT).show()
//            navController.navigate(action)
         }
      })
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   companion object {
      const val TAG = "DiscoverFragment"
   }
}
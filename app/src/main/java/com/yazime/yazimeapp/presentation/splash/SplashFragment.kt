package com.yazime.yazimeapp.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yazime.yazimeapp.R
import com.yazime.yazimeapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

   private var _binding: FragmentSplashBinding? = null
   private val binding get() = _binding!!
   private lateinit var navController: NavController

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      _binding = FragmentSplashBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      navigateToHome()
   }

   private fun navigateToHome() {
      Handler().postDelayed({
         navController.navigate(R.id.action_splashFragment_to_homeFragment)
      }, 3000)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
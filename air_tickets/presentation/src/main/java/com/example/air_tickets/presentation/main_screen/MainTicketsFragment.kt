package com.example.air_tickets.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.air_tickets.presentation.R
import com.example.air_tickets.presentation.common.PLACE_ARRIVAL_KEY
import com.example.air_tickets.presentation.common.PLACE_DEPARTURE_KEY
import com.example.air_tickets.presentation.databinding.FragmentMainTicketsBinding
import com.example.air_tickets.presentation.main_screen.adapter.ViewPagerAdapter
import com.example.air_tickets.presentation.main_screen.adapter.ViewPagerTransformer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainTicketsFragment : Fragment() {

    private val viewModel: MainTicketsViewModel by viewModels()
    private var jobPlaceDeparture: Job? = null
    private var jobPlaceDepartureBottomSheet: Job? = null
    private var jobViewPager: Job? = null

    private var _binding: FragmentMainTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPlaceDepartureEdittext()
        setupPlaceArrivalEdittext()
        setupOffersViewPager()
    }

    private fun setupPlaceDepartureEdittext() {
        jobPlaceDeparture = lifecycleScope.launch {
            viewModel.flowPlaceDeparture.collect { data ->
                binding.edittextPlaceDeparture.setText(data)
                binding.edittextPlaceDeparture.setSelection(binding.edittextPlaceDeparture.length())
            }
        }
        binding.edittextPlaceDeparture.addTextChangedListener {
            savePlaceDeparture(binding.edittextPlaceDeparture.text.toString())
        }
    }

    private fun setupPlaceArrivalEdittext() {
        binding.edittextPlaceArrival.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.edittextPlaceArrival.clearFocus()

                val ticketsBottomSheetDialog = TicketsBottomSheetDialog(
                    requireContext(),
                    viewModel,
                    ::onNavigateCountrySelectedFragment,
                    ::savePlaceDeparture,
                    ::onNavigateToStub
                )
                ticketsBottomSheetDialog.execute()
                ticketsBottomSheetDialog.show()
                ticketsBottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun setupOffersViewPager() {
        viewModel.getOffers()
        jobViewPager = lifecycleScope.launch {
            viewModel.flowListOfferModelData.collect {
                val viewPagerAdapter = ViewPagerAdapter(requireActivity(), it)
                binding.viewpager.adapter = viewPagerAdapter
                val offscreenPageLimit = 3
                binding.viewpager.offscreenPageLimit = offscreenPageLimit
                binding.viewpager.setPageTransformer(ViewPagerTransformer(offscreenPageLimit))
            }
        }
    }

    private fun onNavigateCountrySelectedFragment(placeDeparture: String, placeArrival: String) {
        val bundle = Bundle().apply {
            putString(PLACE_DEPARTURE_KEY, placeDeparture)
            putString(PLACE_ARRIVAL_KEY, placeArrival)
        }
        findNavController().navigate(
            R.id.action_mainTicketsFragment_to_countrySelectedFragment, bundle
        )
    }

    private fun onNavigateToStub() {
        findNavController().navigate(R.id.action_mainTicketsFragment_to_stubFragment)
    }

    private fun savePlaceDeparture(string: String) {
        viewModel.savePlaceDeparture(string)
    }

    override fun onStop() {
        jobPlaceDeparture?.cancel()
        jobPlaceDepartureBottomSheet?.cancel()
        jobViewPager?.cancel()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
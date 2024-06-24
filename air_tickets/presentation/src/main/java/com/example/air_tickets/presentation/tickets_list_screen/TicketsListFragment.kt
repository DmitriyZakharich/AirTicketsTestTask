package com.example.air_tickets.presentation.tickets_list_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.air_tickets.presentation.common.DATA_KEY
import com.example.air_tickets.presentation.common.NUMBER_OF_SEATS_KEY
import com.example.air_tickets.presentation.common.PLACE_ARRIVAL_KEY
import com.example.air_tickets.presentation.common.PLACE_DEPARTURE_KEY
import com.example.air_tickets.presentation.databinding.FragmentTicketsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsListFragment : Fragment() {

    private val viewModel: TicketsListFragmentViewModel by viewModels()
    private var jobTickets: Job? = null
    private var jobRoute: Job? = null
    private var jobDataNumberSeats: Job? = null
    private var _binding: FragmentTicketsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processingIncomingData()
        setupTicketsRecyclerView()
        setupImageViewBack()
    }

    private fun processingIncomingData() {
        arguments?.let {
            viewModel.setData(
                it.getString(PLACE_DEPARTURE_KEY),
                it.getString(PLACE_ARRIVAL_KEY),
                it.getLong(DATA_KEY),
                it.getInt(NUMBER_OF_SEATS_KEY)
            )

            jobRoute = lifecycleScope.launch {
                viewModel.route.collect { data ->
                    binding.routePoints.text = data
                }
            }

            jobDataNumberSeats = lifecycleScope.launch {
                viewModel.dateNumberSeats.collect { data ->
                    binding.dataNumberSeats.text = data
                }
            }
        }
    }

    private fun setupTicketsRecyclerView() {
        viewModel.loadTickets()
        jobTickets = lifecycleScope.launch {
            viewModel.tickets.collect { data ->
                val customRecyclerAdapter = CustomRecyclerAdapter(data, requireActivity())
                binding.ticketsRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
                binding.ticketsRecyclerView.adapter = customRecyclerAdapter
            }
        }
    }

    private fun setupImageViewBack() {
        binding.imageViewPopBackStack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        jobTickets?.cancel()
        jobRoute?.cancel()
        jobDataNumberSeats?.cancel()
        super.onStop()
    }
}
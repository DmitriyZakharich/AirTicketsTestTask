package com.example.air_tickets.presentation.country_selected_screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.air_tickets.presentation.R
import com.example.air_tickets.presentation.common.DATA_KEY
import com.example.air_tickets.presentation.common.NUMBER_OF_SEATS
import com.example.air_tickets.presentation.common.NUMBER_OF_SEATS_KEY
import com.example.air_tickets.presentation.common.NavigateEvent
import com.example.air_tickets.presentation.common.NavigateState
import com.example.air_tickets.presentation.common.PLACE_ARRIVAL_KEY
import com.example.air_tickets.presentation.common.PLACE_DEPARTURE_KEY
import com.example.air_tickets.presentation.common.TIME_FORMAT_1
import com.example.air_tickets.presentation.databinding.DirectFlightsItemBinding
import com.example.air_tickets.presentation.databinding.FragmentCountrySelectedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class CountrySelectedFragment : Fragment() {

    private val viewModel: CountrySelectedViewModel by viewModels()
    private var jobTickets: Job? = null
    private var jobCurrentTime: Job? = null
    private var jobNavigate: Job? = null
    private var _binding: FragmentCountrySelectedBinding? = null
    private val binding get() = _binding!!
    private var timeMilliseconds = 0L

    private var dateAndTime = Calendar.getInstance()

    private val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        dateAndTime.set(Calendar.YEAR, year)
        dateAndTime.set(Calendar.MONTH, month)
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        viewModel.timeFormatting(dateAndTime.timeInMillis, TIME_FORMAT_1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountrySelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processingIncomingData()
        setupDataChip()
        setupTicketsItems()
        setupNavigation()
        setupShowAllButton()
        setupImageViewReplace()
        setupImageViewBack()
    }

    private fun processingIncomingData() {
        arguments?.let {
            binding.edittextPlaceDeparture.setText(it.getString(PLACE_DEPARTURE_KEY).toString())
            binding.edittextPlaceArrival.setText(it.getString(PLACE_ARRIVAL_KEY).toString())
        }
    }

    private fun setupDataChip() {
        viewModel.loadCurrentTime(TIME_FORMAT_1)
        jobCurrentTime = lifecycleScope.launch {
            viewModel.time.collect { data ->
                binding.chipData.text = data.formatedData
                timeMilliseconds = data.timeMilliseconds
            }
        }

        binding.chipData.setOnClickListener {
            DatePickerDialog(
                requireActivity(), dateSetListener,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setupTicketsItems() {
        viewModel.loadTickets()
        val bindingList = getBindingList()
        jobTickets = lifecycleScope.launch {
            viewModel.tickets.collect { data ->
                if (data.size != 3) {
                    Log.e("Log.e", getString(R.string.no_data))
                    return@collect
                }

                bindingList.forEachIndexed { index, item ->
                    item.title.text = data[index].title
                    item.timeRange.text = data[index].timeRange
                    item.price.text = data[index].price
                }

                with(binding) {
                    firstItem.imageCircle.setImageResource(R.drawable.image_red_circle)
                    secondItem.imageCircle.setImageResource(R.drawable.image_blue_circle)
                    firstItem.imageCircle.setImageResource(R.drawable.image_white_circle)
                }
            }
        }
    }

    private fun getBindingList(): List<DirectFlightsItemBinding> =
        listOf(binding.firstItem, binding.secondItem, binding.thirdItem)

    private fun setupNavigation() {
        jobNavigate = lifecycleScope.launch {
            viewModel.navigateState.collect {
                when (it) {
                    NavigateState.Idle -> {}
                    is NavigateState.NavigateTo -> findNavController().navigate(it.id, it.bundle)
                    NavigateState.PopBackStack -> findNavController().popBackStack()
                }
            }
        }
    }

    private fun setupShowAllButton() {

        binding.showAll.setOnClickListener {
            val bundle = preparingPassData()

            viewModel.navigate(
                NavigateEvent.NavigateTo(
                    R.id.action_countrySelectedFragment_to_ticketsListFragment,
                    bundle
                )
            )
        }
    }

    private fun setupImageViewReplace() {
        binding.imageViewReplace.setOnClickListener {
            val string = binding.edittextPlaceDeparture.text
            binding.edittextPlaceDeparture.text = binding.edittextPlaceArrival.text
            binding.edittextPlaceArrival.text = string
        }
    }

    private fun setupImageViewBack() {
        binding.imageViewBack.setOnClickListener {
            viewModel.navigate(NavigateEvent.PopBackStack)
        }
    }

    private fun preparingPassData() = Bundle().apply {
        putString(PLACE_DEPARTURE_KEY, binding.edittextPlaceDeparture.text.toString())
        putString(PLACE_ARRIVAL_KEY, binding.edittextPlaceArrival.text.toString())
        putLong(DATA_KEY, timeMilliseconds)
        putInt(NUMBER_OF_SEATS_KEY, NUMBER_OF_SEATS)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        jobTickets?.cancel()
        jobCurrentTime?.cancel()
        jobNavigate?.cancel()
        super.onStop()
    }
}
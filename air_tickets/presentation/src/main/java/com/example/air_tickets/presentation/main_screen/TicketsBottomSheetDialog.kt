package com.example.air_tickets.presentation.main_screen

import android.content.Context
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.air_tickets.presentation.R
import com.example.air_tickets.presentation.databinding.ModalWindowLayoutBinding
import com.example.air_tickets.presentation.databinding.PopularDestinationItemBinding
import com.example.air_tickets.presentation.databinding.QuickButtonItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TicketsBottomSheetDialog(
    private val context: Context,
    private val viewModel: MainTicketsViewModel,
    private val onNavigate: (String, String) -> Unit,
    private val savePlaceDeparture: (String) -> Unit,
    private val navigateToStub: () -> Unit,
    private val ticketsBottomSheetDialogViewModel: TicketsBottomSheetDialogViewModel
) : BottomSheetDialog(context) {

    private var job: Job? = null
    private var jobDataLoaderShortcutsInfo: Job? = null
    private var jobPopularDestinations: Job? = null
    private val inflater = LayoutInflater.from(context)
    private val binding = ModalWindowLayoutBinding.inflate(inflater)
    private val shortcutButtons: List<QuickButtonItemBinding>
    private val popularDestinationsButtons: List<PopularDestinationItemBinding>

    init {
        setContentView(binding.root)

        shortcutButtons = listOf(
            binding.buttonDifficultRoute,
            binding.buttonAnywhere,
            binding.buttonWeekend,
            binding.buttonHotTickets
        )

        popularDestinationsButtons = listOf(
            binding.popularDestinationItem1,
            binding.popularDestinationItem2,
            binding.popularDestinationItem3
        )

        setupHotkeyButtons()
        setupPopularDestinationItems()
        setupEdittextPlaceDeparture()
        setupEdittextPlaceArrival()
    }

    private fun setupHotkeyButtons() {
        binding.buttonDifficultRoute.itemLayout.setOnClickListener {
            this.dismiss()
            navigateToStub()
        }
        binding.buttonAnywhere.itemLayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(com.example.base.R.string.anywhere))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
        binding.buttonWeekend.itemLayout.setOnClickListener {
            this.dismiss()
            navigateToStub()
        }
        binding.buttonHotTickets.itemLayout.setOnClickListener {
            this.dismiss()
            navigateToStub()
        }
    }

    private fun setupPopularDestinationItems() {
        binding.popularDestinationItem1.constraintlayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(com.example.base.R.string.istanbul))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
        binding.popularDestinationItem2.constraintlayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(com.example.base.R.string.sochi))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
        binding.popularDestinationItem3.constraintlayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(com.example.base.R.string.phuket))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
    }

    private fun setupEdittextPlaceDeparture() {
        binding.edittextPlaceDeparture.addTextChangedListener {
            savePlaceDeparture(binding.edittextPlaceDeparture.text.toString())
        }
    }

    private fun setupEdittextPlaceArrival() {
        binding.edittextPlaceArrival.requestFocus()
        binding.edittextPlaceArrival.setOnKeyListener { _, keyCode, _ ->
            var consumed = false
            if (keyCode == KEYCODE_ENTER && binding.edittextPlaceArrival.text.toString()
                    .isNotBlank()
            ) {
                this.dismiss()
                consumed = true
                onNavigate(
                    binding.edittextPlaceDeparture.text.toString(),
                    binding.edittextPlaceArrival.text.toString()
                )
                job?.cancel()
            }
            consumed
        }
    }

    fun execute() {

        ticketsBottomSheetDialogViewModel.getListShortcutsInfo()
        ticketsBottomSheetDialogViewModel.getListPopularDestinations()

        jobDataLoaderShortcutsInfo = lifecycleScope.launch {
            ticketsBottomSheetDialogViewModel.flowListShortcutsInfo.collect { data ->

                if (shortcutButtons.size != data.size) {
                    return@collect
                }

                data.forEachIndexed { index, item ->
                    shortcutButtons[index].imageview.setImageResource(item.imageResource)
                    shortcutButtons[index].textview.text = context.getString(item.stringResource)
                }
            }

        }

        jobPopularDestinations = lifecycleScope.launch {
            ticketsBottomSheetDialogViewModel.flowListPopularDestinations.collect { data ->

                if (popularDestinationsButtons.size != data.size) {
                    return@collect
                }

                data.forEachIndexed { index, item ->
                    popularDestinationsButtons[index].imageView.setImageResource(item.imageResource)
                    popularDestinationsButtons[index].title.text =
                        context.getString(item.stringResource)
                }
            }

        }

        job = lifecycleScope.launch {
            viewModel.flowPlaceDeparture.collect { data ->
                binding.edittextPlaceDeparture.setText(data)
                binding.edittextPlaceDeparture.setSelection(data.length)
            }
        }
    }

    override fun dismiss() {
        job?.cancel()
        jobDataLoaderShortcutsInfo?.cancel()
        jobPopularDestinations?.cancel()
        super.dismiss()
    }
}
package com.example.air_tickets.presentation.main_screen

import android.content.Context
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.air_tickets.presentation.R
import com.example.air_tickets.presentation.databinding.ModalWindowLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TicketsBottomSheetDialog(
    private val context: Context,
    private val viewModel: MainTicketsViewModel,
    private val onNavigate: (String, String) -> Unit,
    private val savePlaceDeparture: (String) -> Unit,
    private val onNavigateToStub: () -> Unit,
) : BottomSheetDialog(context) {

    private var job: Job? = null
    private val inflater = LayoutInflater.from(context)
    private val binding = ModalWindowLayoutBinding.inflate(inflater)

    init {
        setContentView(binding.root)
        setupAppearance()
        setupHotkeyButtons()
        setupPopularDestinationItems()
        setupEdittextPlaceDeparture()
        setupEdittextPlaceArrival()
    }

    private fun setupAppearance() {
        with(binding) {
            buttonDifficultRoute.imageview.setImageResource(R.drawable.image_difficult_route)
            buttonDifficultRoute.textview.text = context.getString(R.string.difficult_route)
            buttonAnywhere.imageview.setImageResource(R.drawable.image_anywhere)
            buttonAnywhere.textview.text = context.getString(R.string.anywhere)
            buttonWeekend.imageview.setImageResource(R.drawable.image_weekend)
            buttonWeekend.textview.text = context.getString(R.string.weekend)
            buttonHotTickets.imageview.setImageResource(R.drawable.image_hot_tickets)
            buttonHotTickets.textview.text = context.getString(R.string.hot_tickets)

            popularDestinationItem1.title.setText(R.string.istanbul)
            popularDestinationItem1.imageView.setImageResource(R.drawable.image_istanbul)
            popularDestinationItem2.title.setText(R.string.sochi)
            popularDestinationItem2.imageView.setImageResource(R.drawable.image_sochi)
            popularDestinationItem3.title.setText(R.string.phuket)
            popularDestinationItem3.imageView.setImageResource(R.drawable.image_phuket)
        }
    }

    private fun setupHotkeyButtons() {
        binding.buttonDifficultRoute.itemLayout.setOnClickListener {
            this.dismiss()
            onNavigateToStub()
        }
        binding.buttonAnywhere.itemLayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(R.string.anywhere))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
        binding.buttonWeekend.itemLayout.setOnClickListener {
            this.dismiss()
            onNavigateToStub()
        }
        binding.buttonHotTickets.itemLayout.setOnClickListener {
            this.dismiss()
            onNavigateToStub()
        }
    }

    private fun setupPopularDestinationItems() {
        binding.popularDestinationItem1.constraintlayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(R.string.istanbul))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
        binding.popularDestinationItem2.constraintlayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(R.string.sochi))
            binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
        }
        binding.popularDestinationItem3.constraintlayout.setOnClickListener {
            binding.edittextPlaceArrival.setText(context.getString(R.string.phuket))
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
        job = lifecycleScope.launch {
            viewModel.flowPlaceDeparture.collect { data ->
                binding.edittextPlaceDeparture.setText(data)
                binding.edittextPlaceDeparture.setSelection(data.length)
            }
        }
    }
}
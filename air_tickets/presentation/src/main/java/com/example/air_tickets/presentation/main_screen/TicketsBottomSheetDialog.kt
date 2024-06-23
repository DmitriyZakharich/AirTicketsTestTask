package com.example.air_tickets.presentation.main_screen

import android.content.Context
import android.text.InputFilter
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
    private val viewModel: AirTicketsViewModel,
    private val onNavigate: (String, String) -> Unit,
    private val savePlaceDeparture: (String) -> Unit,
) : BottomSheetDialog(context) {

    private var job: Job? = null
    private val inflater = LayoutInflater.from(context)
    private val binding = ModalWindowLayoutBinding.inflate(inflater)

    init {
        setContentView(binding.root)
        setupAppearance()
        setupBehavior()
    }

    private fun setupAppearance() {
        with(binding) {
            quickButtonItem1.imageview.setImageResource(R.drawable.image_difficult_route)
            quickButtonItem1.textview.text = context.getString(R.string.difficult_route)
            quickButtonItem2.imageview.setImageResource(R.drawable.image_anywhere)
            quickButtonItem2.textview.text = context.getString(R.string.anywhere)
            quickButtonItem3.imageview.setImageResource(R.drawable.image_weekend)
            quickButtonItem3.textview.text = context.getString(R.string.weekend)
            quickButtonItem4.imageview.setImageResource(R.drawable.image_hot_tickets)
            quickButtonItem4.textview.text = context.getString(R.string.hot_tickets)

            popularDestinationItem1.title.setText(R.string.istanbul)
            popularDestinationItem1.imageView.setImageResource(R.drawable.image_istanbul)
            popularDestinationItem2.title.setText(R.string.sochi)
            popularDestinationItem2.imageView.setImageResource(R.drawable.image_sochi)
            popularDestinationItem3.title.setText(R.string.phuket)
            popularDestinationItem3.imageView.setImageResource(R.drawable.image_phuket)
        }
    }

    private fun setupBehavior() {
        binding.edittextPlaceDeparture.addTextChangedListener{
            savePlaceDeparture(binding.edittextPlaceDeparture.text.toString())
        }

        binding.quickButtonItem1.itemLayout
            .setOnClickListener {
                binding.edittextPlaceArrival.setText("Сложный маршрут")
                binding.edittextPlaceArrival.setSelection(binding.edittextPlaceArrival.text.toString().length)
            }
        binding.edittextPlaceArrival.requestFocus()

        binding.edittextPlaceArrival.setOnKeyListener { v, keyCode, event ->

            var consumed = false

            if (keyCode == KEYCODE_ENTER) {
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

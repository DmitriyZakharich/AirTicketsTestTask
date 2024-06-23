package com.example.air_tickets.presentation.tickets_list_screen

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.air_tickets.domain.models.TicketModel
import com.example.air_tickets.domain.models.priceFormat
import com.example.air_tickets.presentation.databinding.ListTicketsItemBinding
import java.time.LocalDateTime

class CustomRecyclerAdapter(private val tickets: List<TicketModel>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListTicketsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListTicketsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = tickets.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){

            if (tickets[position].badge.isBlank()) {
                badge.visibility = View.GONE
            } else {
                badge.text = tickets[position].badge
            }

            price.text = tickets[position].price.value.priceFormat()
            departureTime.text = extractTime(tickets[position].departureModel.date)
            departureAirportCode.text = tickets[position].departureModel.airport
            arrivalTime.text = extractTime(tickets[position].arrivalModel.date)
            arrivalAirportCode.text = tickets[position].arrivalModel.airport
            flightTime.text = "TODO"    //todo
            transfer.text = "TODO"      //todo

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun extractTime(string: String): String {
        val dateTime = LocalDateTime.parse(string)
        val hours = dateTime.hour
        val minute = dateTime.minute
        return String.format("%02d:%02d", hours, minute)
    }
}
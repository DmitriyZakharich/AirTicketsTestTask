package com.example.air_tickets.presentation.main_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.air_tickets.domain.models.OfferView
import com.example.air_tickets.presentation.R
import com.example.air_tickets.presentation.databinding.ItemOfferBinding

class ViewPagerAdapter(private val context: Context, private val list: List<OfferView>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root)

    private val images = listOf(
        R.drawable.image_offer_1,
        R.drawable.image_offer_2,
        R.drawable.image_offer_3
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            title.text = list[position].title
            town.text = list[position].town
            price.text = list[position].price

            try {
                imageView.setImageResource(images[position])
            } catch (ex: Exception) {
                Toast.makeText(context, context.getString(R.string.error_image), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
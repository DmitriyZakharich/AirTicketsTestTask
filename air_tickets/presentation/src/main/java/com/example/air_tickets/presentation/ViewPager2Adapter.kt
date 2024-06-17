package com.example.air_tickets.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Px
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class ViewPager2Adapter(private val context: Context, private val list: List<String>): RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_offer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.city.text = list[position]
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city: TextView = itemView.findViewById(R.id.city)
    }
}

class OffsetPageTransformer(
    @Px private val offsetPx: Int,
    @Px private val pageMarginPx: Int
) : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val viewPager = requireViewPager(page)
        val offset = position * -(2 * offsetPx + pageMarginPx)
        val totalMargin = offsetPx + pageMarginPx

        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            page.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                marginStart = totalMargin
                marginEnd = totalMargin
            }

            page.translationX = if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                -offset
            } else {
                offset
            }
        } else {
            page.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = totalMargin
                bottomMargin = totalMargin
            }

            page.translationY = offset
        }
    }

    private fun requireViewPager(page: View): ViewPager2 {
        val parent = page.parent
        val parentParent = parent.parent
        if (parent is RecyclerView && parentParent is ViewPager2) {
            return parentParent
        }
        throw IllegalStateException(
            "Expected the page view to be managed by a ViewPager2 instance."
        )
    }
}
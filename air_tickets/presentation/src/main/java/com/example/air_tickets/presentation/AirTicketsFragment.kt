package com.example.air_tickets.presentation

import android.content.res.Resources.getSystem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2


class AirTicketsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_air_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewpager)

        val viewPager2Adapter = ViewPager2Adapter(requireActivity(), listOf("1", "2", "3"))
        viewPager2.adapter = viewPager2Adapter
        viewPager2.setPageTransformer(OffsetPageTransformer(30.px, 20.px))

        viewPager2.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            }
        )
    }
}

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

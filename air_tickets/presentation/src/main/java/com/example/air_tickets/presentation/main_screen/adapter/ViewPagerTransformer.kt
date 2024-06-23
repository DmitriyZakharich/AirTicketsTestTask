package com.example.air_tickets.presentation.main_screen.adapter

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ViewPagerTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {
        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 2.5f
        private const val DEFAULT_SCALE = 1f
        private const val DEFAULT_ALPHA = 1f
    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            ViewCompat.setElevation(page, -abs(position))
            when {
                position <= 0f -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA + position
                }
                position <= offscreenPageLimit - 1 -> {
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                }
                else -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA
                }
            }
        }
    }
}
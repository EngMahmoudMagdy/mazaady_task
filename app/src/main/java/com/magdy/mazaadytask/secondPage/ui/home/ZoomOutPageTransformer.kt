package com.magdy.mazaadytask.secondPage.ui.home

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val minScale = 0.5f
        val minAlpha = 0.5f

        val pageWidth = page.width.toFloat()
        val scaleFactor = minScale + (1 - minScale) * (1 - abs(position))
        val alphaFactor = minAlpha + (1 - minAlpha) * (1 - abs(position))

        page.translationX = -pageWidth * position
        page.scaleX = scaleFactor
        page.scaleY = scaleFactor
        page.alpha = alphaFactor
    }
}

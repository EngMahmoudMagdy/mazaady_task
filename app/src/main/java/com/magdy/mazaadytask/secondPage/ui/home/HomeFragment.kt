package com.magdy.mazaadytask.secondPage.ui.home

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.magdy.mazaadytask.R
import com.magdy.mazaadytask.databinding.FragmentHomeBinding
import kotlin.math.abs

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        val items = listOf(1, 2, 3, 4)
        val adapter = SliderAdapter(items)
        slider.adapter = adapter
        slider.offscreenPageLimit = 3
        slider.addCarouselEffect(enableZoom = true)
        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == adapter.itemCount - 1) {
                    slider.setCurrentItem(1, false)
                } else if (position == 0) {
                    slider.setCurrentItem(adapter.itemCount - 2, false)
                }
            }
        })
        TabLayoutMediator(indicatorLayout, slider) { tab, position ->
            //Some implementation
        }.attach()

        val storyAdapter = StoryAdapter(listOf(1, 2, 3, 4, 5, 6, 7))
        storiesRecycler.adapter = storyAdapter
    }

    fun ViewPager2.addCarouselEffect(enableZoom: Boolean = true) {
        clipChildren = false    // No clipping the left and right items
        clipToPadding = false   // Show the viewpager in full width without clipping the padding
        offscreenPageLimit = 3  // Render the left and right items
        (getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * Resources.getSystem().displayMetrics.density).toInt()))
        if (enableZoom) {
            compositePageTransformer.addTransformer { page, position ->
                page.translationX = -pageTranslationX * position
                val r = 1 - abs(position)
                page.scaleY = (0.80f + r * 0.20f)
                page.alpha = 0.25f + (1 - abs(position))
            }
        }
        setPageTransformer(compositePageTransformer)
    }
}
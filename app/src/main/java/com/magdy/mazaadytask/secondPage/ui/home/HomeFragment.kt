package com.magdy.mazaadytask.secondPage.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
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
        slider.setPageTransformer(addTransformer())

        TabLayoutMediator(indicatorLayout, slider) { tab, position ->
            //Some implementation
        }.attach()

        val storyAdapter = StoryAdapter(listOf(1, 2, 3, 4, 5, 6, 7))
        storiesRecycler.adapter = storyAdapter
    }

    private fun addTransformer(): CompositePageTransformer {
        return CompositePageTransformer().apply {
            addTransformer { page, position ->
                val offset = 30
                when {
                    position < -1 -> { // Page is off to the left
                        page.translationX = 0f
                        page.scaleX = 1f
                        page.scaleY = 1f
                    }
                    position <= 0 -> { // Page is in the process of sliding in
                        page.translationX = -offset * position
                        page.scaleX = 1f
                        page.scaleY = 1f
                    }
                    position <= 1 -> { // Page is in the process of sliding out
                        page.translationX = offset * -position
                        page.scaleX = 1f
                        page.scaleY = 1f
                    }
                    else -> { // Page is off to the right
                        page.translationX = 0f
                        page.scaleX = 1f
                        page.scaleY = 1f
                    }
                }
            }
        }
    }

}
package com.magdy.mazaadytask.secondPage.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.magdy.mazaadytask.R
import com.magdy.mazaadytask.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        val items = listOf(1, 2, 3, 4)
        val adapter = SliderAdapter(items)
        slider.adapter = adapter
        layoutManager = ProminentLayoutManager(requireContext())
        slider.layoutManager = layoutManager
        createDashIndicator(items.size)
    }

    private fun updateIndicator(position: Int) {
        for (i in 0 until binding.indicatorLayout.childCount) {
            val indicator = binding.indicatorLayout.getChildAt(i)
            indicator.setBackgroundResource(
                if (i == position) R.drawable.selected_indicator else R.drawable.unselected_indicator
            )
        }
    }

    private fun createDashIndicator(count: Int) {
        val indicatorLayout = binding.indicatorLayout
        for (i in 0 until count) {
            val indicator = View(requireContext())
            indicator.setBackgroundResource(R.drawable.unselected_indicator)
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 0, 0)
            indicator.layoutParams = params
            indicatorLayout.addView(indicator)
        }
    }

    private fun initRecyclerViewPosition(position: Int) {
        // This initial scroll will be slightly off because it doesn't
        // respect the SnapHelper. Do it anyway so that the target view
        // is laid out, then adjust onPreDraw.
        val snapHelper = PagerSnapHelper()

        layoutManager.scrollToPosition(position)

        binding.slider.doOnPreDraw {
            val targetView = layoutManager.findViewByPosition(position)
                ?: return@doOnPreDraw

            val distanceToFinalSnap =
                snapHelper.calculateDistanceToFinalSnap(layoutManager, targetView)
                    ?: return@doOnPreDraw

            layoutManager.scrollToPositionWithOffset(position, -distanceToFinalSnap[0])

        }
    }
    }
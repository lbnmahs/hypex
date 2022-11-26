package com.example.hypex.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hypex.R
import com.example.hypex.adapters.HomeViewpagerAdapter
import com.example.hypex.databinding.FragmentHomeBinding
import com.example.hypex.fragments.categories.*
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            HeadphonesFragment(),
            MonitorsFragment(),
            KeyboardsFragment(),
            MouseFragment(),
            LaptopsFragment()
        )

        val viewPager2Adapter = HomeViewpagerAdapter(categoryFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome){ tab, position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Headphones"
                2 -> tab.text = "Monitors"
                3 -> tab.text = "Keyboards"
                4 -> tab.text = "Mouse"
                5 -> tab.text = "Laptops"
            }
        }.attach()
    }
}


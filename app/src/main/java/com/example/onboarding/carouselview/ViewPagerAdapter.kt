package com.example.onboarding.carouselview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.onboarding.carouselview.CarouselPage
import com.example.onboarding.carouselview.CarouselPageFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private var pages = mutableListOf<CarouselPage>()

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment = CarouselPageFragment(pages[position])

    fun setPages(pages: List<CarouselPage>) {
        this.pages.addAll(pages)
    }
}
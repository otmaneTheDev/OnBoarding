package com.example.onboarding.carouselview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.onboarding.R
import kotlinx.android.synthetic.main.fragment_carousel_page.*

class CarouselPageFragment(private val page: CarouselPage) : Fragment(R.layout.fragment_carousel_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carouselPageImage.setImageResource(page.image)
        carouselPageTitle.setText(page.title)
        carouselPageText.setText(page.text)
    }
}
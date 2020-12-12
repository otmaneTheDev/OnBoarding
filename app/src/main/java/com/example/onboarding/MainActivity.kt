package com.example.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.onboarding.carouselview.CarouselPage
import com.example.onboarding.carouselview.CarouselPager
import com.example.onboarding.carouselview.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CarouselPager.CarouselListener {

    private val carouselPages = listOf(
        CarouselPage(R.drawable.carousel_image, R.string.onBoarding1_title, R.string.onBoarding1_text),
        CarouselPage(R.drawable.carousel_image, R.string.onBoarding2_title, R.string.onBoarding2_text),
        CarouselPage(R.drawable.carousel_image, R.string.onBoarding3_title, R.string.onBoarding3_text)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carouselPager
            .setUpCarousel(this, carouselPages)
            .setUpPageTransformer(ZoomOutPageTransformer())
            .setUpCarouselListener(this)
    }

    override fun onCarouselFinished(skipped: Boolean) {
        val carouselStatus = if (skipped) "skipped" else "completed"
        Toast.makeText(this, "You've $carouselStatus the on boarding.", Toast.LENGTH_SHORT).show()
    }
}
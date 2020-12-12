package com.example.onboarding.carouselview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.onboarding.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.carousel_pager.view.*

class CarouselPager(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attributeSet, defStyleAttr) {
    constructor(context: Context, attributeSet: AttributeSet? = null) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null)

    private val zoomInAnim = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
    private val carouselPages = mutableListOf<CarouselPage>()
    private var carouselListener: CarouselListener? = null

    init {
        inflateView()
        setUpListeners()
    }

    private fun inflateView() {
        View.inflate(context, R.layout.carousel_pager, this)
    }

    private fun setUpListeners() {
        btnGetStarted.setOnClickListener {
            carouselListener?.onCarouselFinished()
        }

        btnSkip.setOnClickListener {
            carouselListener?.onCarouselFinished(true)
        }
    }

    fun setUpCarousel(fragmentActivity: FragmentActivity, carouselPages: List<CarouselPage>): CarouselPager {
        val viewPagerAdapter = ViewPagerAdapter(fragmentActivity)
        viewPagerAdapter.setPages(carouselPages)

        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == carouselPages.size - 1) {
                    btnGetStarted.visibility = View.VISIBLE
                    btnGetStarted.startAnimation(zoomInAnim)
                    btnSkip.visibility = View.GONE
                } else {
                    btnGetStarted.visibility = View.GONE
                    btnSkip.visibility = View.VISIBLE
                }
            }
        })

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        this.carouselPages.addAll(carouselPages)
        return this
    }

    fun setUpPageTransformer(pageTransformer: ViewPager2.PageTransformer): CarouselPager {
        viewPager.setPageTransformer(pageTransformer)
        return this
    }

    fun setUpCarouselListener(carouselListener: CarouselListener): CarouselPager {
        this.carouselListener = carouselListener
        return this
    }

    interface CarouselListener {
        fun onCarouselFinished(skipped: Boolean = false)
    }
}
package com.nhatanh.commonscreensdk.splash

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.nhatanh.commonscreensdk.splash.BaseActivity
import com.nhatanh.commonscreensdk.R
import com.nhatanh.commonscreensdk.databinding.ActivitySplashBinding
import com.nhatanh.commonscreensdk.extensions.invisible
import com.nhatanh.commonscreensdk.extensions.setPref
import com.nhatanh.commonscreensdk.extensions.show
import com.nhatanh.commonscreensdk.`interface`.ICallBackProgress
import com.nhatanh.commonscreensdk.util.LocaleHelper
import com.nhatanh.commonscreensdk.util.PREFERENCE_SELECTED_LANGUAGE

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private var logoView: ImageView? = null
    private var nameAppView: TextView? = null
    private var loadingView: LottieAnimationView? = null

    override fun bindData() {}
    override fun bindEvent() {}
    override fun initializeArgument() {}
    override fun bindView() {}

    override fun bindComponent() {
        setPref(this, PREFERENCE_SELECTED_LANGUAGE, "")
        LocaleHelper.setLocale(this)
        
        // Tìm views bằng custom IDs từ config
        // Find views using custom IDs from config
        findViewsByCustomIds()
        
        animateLogoDrop()

        Handler(Looper.getMainLooper()).postDelayed({
            onNextAction()
        }, SplashSDKConfig.splashDuration)

        loadProgressBarAnimation()
    }

    /**
     * Tìm các view bằng custom IDs từ SplashSDKConfig
     * Find views using custom IDs from SplashSDKConfig
     */
    private fun findViewsByCustomIds() {
        try {
            // Tìm logo view
            val logoViewFound = binding.root.findViewById<View>(SplashSDKConfig.logoId)
            logoView = if (logoViewFound is ImageView) logoViewFound else null

            // Tìm nameApp view
            val nameAppViewFound = binding.root.findViewById<View>(SplashSDKConfig.nameAppId)
            nameAppView = if (nameAppViewFound is TextView) nameAppViewFound else null

            // Tìm loading view
            val loadingViewFound = binding.root.findViewById<View>(SplashSDKConfig.loadingId)
            loadingView = if (loadingViewFound is LottieAnimationView) loadingViewFound else null
        } catch (e: Exception) {
            // Fallback về default views nếu không tìm thấy custom IDs
            // Fallback to default views if custom IDs not found
            logoView = binding.logo
            nameAppView = binding.nameApp
            loadingView = binding.loading
        }
    }

    private fun loadProgressBarAnimation() {
        binding.loadFile.onProgress = object : ICallBackProgress {
            @SuppressLint("SetTextI18n")
            override fun onProgress(progress: Int) {
                binding.tvLoading.text = "${getString(R.string.loading)} ($progress%)..."
            }
        }
    }

    /**
     * Gọi callback khi splash screen kết thúc
     * Call callback when splash screen finishes
     */
    private fun onNextAction() {
        SplashSDKConfig.splashCallback?.onNextAction()
        finish()
    }

    /**
     * Animation cho logo drop
     * Animation for logo drop
     */
    private fun animateLogoDrop() {
        // Sử dụng logo view từ custom ID hoặc default
        // Use logo view from custom ID or default
        val logoContainer = logoView?.parent as? View ?: binding.imgApp
        
        logoContainer.invisible()
        logoContainer.alpha = 0f
        logoContainer.translationY = -500f
        logoContainer.show()

        val dropAnimator = ObjectAnimator.ofPropertyValuesHolder(
            logoContainer,
            PropertyValuesHolder.ofFloat("translationY", -500f, 0f),
            PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        )

        dropAnimator.duration = 2000
        dropAnimator.interpolator = BounceInterpolator()
        dropAnimator.start()
    }
}
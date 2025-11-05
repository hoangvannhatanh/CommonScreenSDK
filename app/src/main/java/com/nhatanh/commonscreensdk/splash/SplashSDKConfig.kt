package com.nhatanh.commonscreensdk.splash

import com.nhatanh.commonscreensdk.`interface`.ISplashCallback

/**
 * Class cấu hình cho Splash SDK
 * Configuration class for Splash SDK
 */
object SplashSDKConfig {
    /**
     * Callback được gọi khi splash screen kết thúc
     * Callback called when splash screen finishes
     */
    var splashCallback: ISplashCallback? = null

    /**
     * Resource ID cho logo image view
     * Resource ID for logo image view
     */
    var logoId: Int = com.nhatanh.commonscreensdk.R.id.logo

    /**
     * Resource ID cho app name text view
     * Resource ID for app name text view
     */
    var nameAppId: Int = com.nhatanh.commonscreensdk.R.id.nameApp

    /**
     * Resource ID cho loading animation view
     * Resource ID for loading animation view
     */
    var loadingId: Int = com.nhatanh.commonscreensdk.R.id.loading

    /**
     * Thời gian hiển thị splash screen (milliseconds)
     * Splash screen display duration (milliseconds)
     */
    var splashDuration: Long = 1500L

    /**
     * Reset cấu hình về mặc định
     * Reset configuration to default
     */
    fun reset() {
        splashCallback = null
        logoId = com.nhatanh.commonscreensdk.R.id.logo
        nameAppId = com.nhatanh.commonscreensdk.R.id.nameApp
        loadingId = com.nhatanh.commonscreensdk.R.id.loading
        splashDuration = 1500L
    }
}


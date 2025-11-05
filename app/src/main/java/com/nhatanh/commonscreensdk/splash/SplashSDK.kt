package com.nhatanh.commonscreensdk.splash

import android.content.Context
import android.content.Intent
import com.nhatanh.commonscreensdk.R
import com.nhatanh.commonscreensdk.`interface`.ISplashCallback

/**
 * Public API class cho Splash SDK
 * Các project khác import và sử dụng class này để gọi màn hình splash
 * 
 * Public API class for Splash SDK
 * Other projects import and use this class to launch splash screen
 */
object SplashSDK {
    /**
     * Khởi động màn hình splash với callback
     * Launch splash screen with callback
     * 
     * @param context Context của ứng dụng
     * @param callback Callback được gọi khi splash kết thúc
     * @param logoId Resource ID cho logo (optional, mặc định là R.id.logo)
     * @param nameAppId Resource ID cho app name (optional, mặc định là R.id.nameApp)
     * @param loadingId Resource ID cho loading (optional, mặc định là R.id.loading)
     * @param splashDuration Thời gian hiển thị splash (milliseconds, mặc định 1500ms)
     */
    fun launch(
        context: Context,
        callback: ISplashCallback,
        logoId: Int = R.id.logo,
        nameAppId: Int = R.id.nameApp,
        loadingId: Int = R.id.loading,
        splashDuration: Long = 1500L
    ) {
        // Cấu hình SDK
        SplashSDKConfig.splashCallback = callback
        SplashSDKConfig.logoId = logoId
        SplashSDKConfig.nameAppId = nameAppId
        SplashSDKConfig.loadingId = loadingId
        SplashSDKConfig.splashDuration = splashDuration

        // Khởi động SplashActivity
        val intent = Intent(context, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    /**
     * Khởi động màn hình splash với callback đơn giản
     * Launch splash screen with simple callback
     * 
     * @param context Context của ứng dụng
     * @param callback Callback được gọi khi splash kết thúc
     */
    fun launch(context: Context, callback: ISplashCallback) {
        launch(context, callback, R.id.logo, R.id.nameApp, R.id.loading, 1500L)
    }
}


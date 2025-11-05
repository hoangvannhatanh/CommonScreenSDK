package com.nhatanh.commonscreensdk.splash

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nhatanh.commonscreensdk.R
import com.nhatanh.commonscreensdk.util.LocaleHelper

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val resId: Int) :
    AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        fullScreenCall()

        binding = DataBindingUtil.setContentView(this, resId)
        binding.lifecycleOwner = this

        initializeArgument()
        bindComponent()
        bindEvent()
        bindData()
        bindView()
    }

    abstract fun bindComponent()
    abstract fun bindData()
    abstract fun bindEvent()
    abstract fun initializeArgument()
    abstract fun bindView()

    open fun initWindow() {
        window?.apply {
            val background: Drawable = ColorDrawable(Color.parseColor("#FFFFFF"))
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = resources.getColor(R.color.black)
            setBackgroundDrawable(background)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun fullScreenCall() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(base))
    }
}
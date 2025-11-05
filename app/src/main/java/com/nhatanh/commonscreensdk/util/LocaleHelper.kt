package com.nhatanh.commonscreensdk.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.nhatanh.commonscreensdk.R
import com.nhatanh.commonscreensdk.extensions.getPref
import java.util.Locale

object LocaleHelper {
    fun setLocale(c: Context): Context {
        return updateResources(c, getLanguageCode(c))
    }

    private fun getLanguageCode(context: Context): String {
        return getPref(
            context,
            PREFERENCE_SELECTED_LANGUAGE,
            context.getString(R.string.english)
        ).toString()
    }


    private fun updateResources(contextMain: Context, language: String): Context {
        var context: Context = contextMain
        val locale: Locale = when (language) {
            "zh-rTW" -> Locale("zh", "TW")
            "pt-rBR" -> Locale("pt", "BR")
            else -> Locale(language)
        }
        Locale.setDefault(locale)
        val res: Resources = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        context = context.createConfigurationContext(config)
        return context
    }

}
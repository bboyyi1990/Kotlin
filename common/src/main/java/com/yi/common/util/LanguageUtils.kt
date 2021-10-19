package com.yi.common.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

object LanguageUtils {
    const val ZH = "zh"
    const val EN = "en"
    const val LO = "lo"
    const val CURRENT_LANGUAGE = "CURRENT_LANGUAGE"
    var default = EN

    open fun init(application: Application) {
        val current = MemoryUtil.getString(
            CURRENT_LANGUAGE,
            application.resources.configuration.locale.language
        )
        default = if (ZH == current) ZH else EN
    }

    open fun attachBaseContext(newBase: Context): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val resources = newBase.resources
            val configuration = resources.configuration
            val locale = getLocal(MemoryUtil.getString(CURRENT_LANGUAGE))
            configuration.setLocale(locale)
            configuration.setLocales(LocaleList(locale))
            newBase.createConfigurationContext(configuration)
        } else {
            newBase
        }
    }

    open fun changeLanguage(activity: Activity, language: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            val resources = activity.resources
            val configuration = resources.configuration
            val locale = getLocal(language)
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        MemoryUtil.saveString(CURRENT_LANGUAGE, language)
    }

    private fun getLocal(language: String?): Locale =
        when (language) {
            ZH -> Locale.SIMPLIFIED_CHINESE
            LO -> Locale.forLanguageTag(language)
            else -> Locale.ENGLISH
        }
}
package com.yoesuv.kmplanguage

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.LocaleListCompat
import java.util.Locale
import androidx.compose.ui.platform.LocalResources

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual object LocalAppLocale {
    private var default: Locale? = null
    actual val current: String
        @Composable get() = Locale.getDefault().toString()

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val configuration = LocalConfiguration.current

        if (default == null) {
            default = Locale.getDefault()
        }

        val new = when(value) {
            null -> default!!
            else -> Locale(value)
        }
        // Keep legacy Locale.setDefault to satisfy components relying on it
        Locale.setDefault(new)
        configuration.setLocale(new)
        val resources = LocalResources.current

        resources.updateConfiguration(configuration, resources.displayMetrics)
        return LocalConfiguration.provides(configuration)
    }
}

actual fun changeLanguage(language: String) {
    val locales = LocaleListCompat.forLanguageTags(language)
    AppCompatDelegate.setApplicationLocales(locales)
}

actual fun getSavedLanguage(): String {
    val tags = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    if (tags.isEmpty()) {
        return Locale.getDefault().language
    }
    // Return primary language subtag (e.g., "en" from "en-US") to match existing usage
    return tags.substringBefore('-')
}
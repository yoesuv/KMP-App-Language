package com.yoesuv.kmplanguage

import android.os.Build
import android.content.res.Configuration
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
        val currentConfig = LocalConfiguration.current

        if (default == null) {
            default = Locale.getDefault()
        }

        val tag = value?.trim().orEmpty()
        val new = if (tag.isEmpty()) default!! else Locale.forLanguageTag(tag)
        Locale.setDefault(new)
        val configuration = Configuration(currentConfig)
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
    val primary = if (tags.isEmpty()) {
        Locale.getDefault().language
    } else {
        tags.substringBefore('-')
    }
    return primary
}
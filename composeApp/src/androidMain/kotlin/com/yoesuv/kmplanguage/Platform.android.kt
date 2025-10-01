package com.yoesuv.kmplanguage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Build
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.LocaleListCompat
import java.util.Locale
import androidx.compose.ui.platform.LocalResources
import androidx.core.content.edit

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual object LocalAppLocale {
    private var default: Locale? = null

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

        // Persist selection via SharedPreferences, mirroring iOS NSUserDefaults behavior
        persistLanguage(tag.ifEmpty { new.language })
        return LocalConfiguration.provides(configuration)
    }
}

actual fun changeLanguage(language: String) {
    // Persist to SharedPreferences
    persistLanguage(language)

    // Apply at runtime using AppCompatDelegate
    val locales = LocaleListCompat.forLanguageTags(language)
    AppCompatDelegate.setApplicationLocales(locales)
}

actual fun getSavedLanguage(): String {
    // Read from SharedPreferences first; fallback to default/app locales
    val pref = appContextOrNull()?.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    val saved = pref?.getString(LANG_KEY, null)
    if (!saved.isNullOrBlank()) return saved

    val tags = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    val primary = if (tags.isEmpty()) {
        Locale.getDefault().language
    } else {
        tags.substringBefore('-')
    }
    // If still empty, default to Indonesian to mirror iOS default
    return primary.ifEmpty { Language.Indonesia.isoFormat }
}

// ---- SharedPreferences + Context setup ----
private const val PREFS_NAME = "kmp_language_prefs"
private const val LANG_KEY = "app_language"

@SuppressLint("StaticFieldLeak")
private var appCtx: Context? = null

// Call this from Android app (e.g., Application.onCreate) to provide applicationContext
fun setupAndroidLocaleContext(context: Context) {
    appCtx = context.applicationContext
}

private fun appContextOrNull(): Context? = appCtx

private fun persistLanguage(language: String) {
    val ctx = appContextOrNull() ?: return
    ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        .edit {
            putString(LANG_KEY, language)
        }
}
package com.yoesuv.kmplanguage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.InternalComposeUiApi
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.preferredLanguages
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

private const val LANG_KEY = "AppleLanguages"

@OptIn(InternalComposeUiApi::class)
actual object LocalAppLocale {
    
    // Get the saved language from UserDefaults, or use system default
    private fun getSavedLanguage(): String {
        val savedLanguages = NSUserDefaults.standardUserDefaults.objectForKey(LANG_KEY) as? List<*>
        return if (savedLanguages?.isNotEmpty() == true) {
            savedLanguages.first() as String
        } else {
            NSLocale.preferredLanguages.first() as String
        }
    }
    
    private val LocalAppLocale = staticCompositionLocalOf { getSavedLanguage() }
    
    actual val current: String
        @Composable get() = LocalAppLocale.current

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val new = value ?: getSavedLanguage()
        
        // Set the user defaults for persistence
        if (value == null) {
            NSUserDefaults.standardUserDefaults.removeObjectForKey(LANG_KEY)
        } else {
            NSUserDefaults.standardUserDefaults.setObject(arrayListOf(new), LANG_KEY)
            NSUserDefaults.standardUserDefaults.synchronize()
        }
        
        return LocalAppLocale.provides(new)
    }
}

actual fun changeLanguage(language: String) {
    // Set the language in NSUserDefaults for system-level persistence
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(language), LANG_KEY)
    NSUserDefaults.standardUserDefaults.synchronize()
}

actual fun getSavedLanguage(): String {
    val savedLanguages = NSUserDefaults.standardUserDefaults.objectForKey(LANG_KEY) as? List<*>
    return if (savedLanguages?.isNotEmpty() == true) {
        savedLanguages.first() as String
    } else {
        Language.Indonesia.isoFormat // Default to Indonesian
    }
}
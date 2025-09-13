package com.yoesuv.kmplanguage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

sealed class Language(val isoFormat: String) {
    data object English : Language("en")
    data object Indonesia : Language("id")
}

@Composable
fun LocalizedApp(language: String = Language.Indonesia.isoFormat, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppLocale provides language) {
        content()
    }
}
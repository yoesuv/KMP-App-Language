package com.yoesuv.kmplanguage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun LocalizedApp(language: String = Language.Indonesia.isoFormat, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppLocale provides language) {
        content()
    }
}
package com.yoesuv.kmplanguage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect object LocalAppLocale {
    val current: String @Composable get
    @Composable infix fun provides(value: String?): ProvidedValue<*>
}

expect fun changeLanguage(language: String)

expect fun getSavedLanguage(): String
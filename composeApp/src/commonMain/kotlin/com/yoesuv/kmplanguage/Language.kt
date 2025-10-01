package com.yoesuv.kmplanguage

sealed class Language(val isoFormat: String) {
    data object English : Language("en")
    data object Indonesia : Language("id")
}

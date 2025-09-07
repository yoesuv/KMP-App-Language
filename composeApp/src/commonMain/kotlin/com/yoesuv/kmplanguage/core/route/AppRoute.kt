package com.yoesuv.kmplanguage.core.route

import kotlinx.serialization.Serializable

sealed class AppRoute {
    @Serializable
    data object Home : AppRoute()
    @Serializable
    data object Settings : AppRoute()
}
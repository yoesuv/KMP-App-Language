package com.yoesuv.kmplanguage

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
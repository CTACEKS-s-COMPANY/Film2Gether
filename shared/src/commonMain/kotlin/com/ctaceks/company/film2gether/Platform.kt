package com.ctaceks.company.film2gether

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
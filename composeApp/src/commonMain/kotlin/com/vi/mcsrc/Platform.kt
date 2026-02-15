package com.vi.mcsrc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
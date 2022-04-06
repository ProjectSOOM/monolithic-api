package com.soom.monolithic_api.global.jwt.util

interface JwtUtil<T> {
    fun encode(payload: T): String
    fun decode(token: String): T
}
package com.soom.monolithic_api.global.token.jwt.util

import com.soom.monolithic_api.global.token.TokenUtil

interface JwtUtil<T>: TokenUtil<T, String> {
}
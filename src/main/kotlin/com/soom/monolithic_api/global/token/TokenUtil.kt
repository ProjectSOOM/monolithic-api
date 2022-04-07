package com.soom.monolithic_api.global.token

interface TokenUtil<T, T2> {
    fun encode(payload: T): T2
    fun decode(token: T2): T
}
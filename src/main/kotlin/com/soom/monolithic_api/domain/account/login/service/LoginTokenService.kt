package com.soom.monolithic_api.domain.account.login.service

interface LoginTokenService {
    fun accessToken(id: Long): String
    fun refreshToken(id: Long): String
    fun decode(token: String): Long
}

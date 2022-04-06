package com.soom.monolithic_api.domain.account.auth.service

interface EmailTokenService {
    fun generateEmailToken(email: String): String
}

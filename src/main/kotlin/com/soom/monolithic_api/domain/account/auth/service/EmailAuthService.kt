package com.soom.monolithic_api.domain.account.auth.service

interface EmailAuthService {
    fun addAuthData(code: String, email: String)
    fun sendAuthCodeToEmail(code: String, email: String)
    fun getEmailByAuthCode(code: String): String
}

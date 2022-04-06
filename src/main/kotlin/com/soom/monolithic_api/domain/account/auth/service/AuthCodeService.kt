package com.soom.monolithic_api.domain.account.auth.service

interface AuthCodeService {
    fun generateAuthCode(): String
}
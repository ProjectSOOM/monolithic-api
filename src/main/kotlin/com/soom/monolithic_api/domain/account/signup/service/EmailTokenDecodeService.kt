package com.soom.monolithic_api.domain.account.signup.service

interface EmailTokenDecodeService {
    fun decode(emailToken: String): String
}

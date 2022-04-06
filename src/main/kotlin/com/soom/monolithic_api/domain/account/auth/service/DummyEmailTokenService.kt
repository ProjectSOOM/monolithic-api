package com.soom.monolithic_api.domain.account.auth.service

import org.springframework.stereotype.Service

@Service
class DummyEmailTokenService: EmailTokenService {
    override fun generateEmailToken(email: String): String = "email-token-$email"
}
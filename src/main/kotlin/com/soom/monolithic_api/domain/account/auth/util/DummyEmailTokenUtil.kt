package com.soom.monolithic_api.domain.account.auth.util

import org.springframework.stereotype.Service

@Service
class DummyEmailTokenUtil: EmailTokenUtil {
    override fun encode(email: String): String = "email-token-$email"
    override fun decode(token: String): String = token.replace("email-token-", "")
}
package com.soom.monolithic_api.domain.account.auth.util

class DummyEmailTokenUtil: EmailTokenUtil {
    override fun encode(payload: String): String = "email-token-$payload"
    override fun decode(token: String): String = token.replace("email-token-", "")
}
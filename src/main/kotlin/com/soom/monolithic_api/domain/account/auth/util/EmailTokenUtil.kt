package com.soom.monolithic_api.domain.account.auth.util

import com.soom.monolithic_api.global.token.TokenUtil

interface EmailTokenUtil: TokenUtil<String, String> {
    override fun encode(payload: String): String
}

package com.soom.monolithic_api.domain.account.auth.util

import com.soom.monolithic_api.global.token.jwt.util.StandardJwtUtil
import io.jsonwebtoken.Claims
import java.time.LocalDateTime

class JwtEmailTokenUtil: EmailTokenUtil, StandardJwtUtil<String>() {
    override fun getExpiredAt(now: LocalDateTime): LocalDateTime {
        TODO("Not yet implemented")
    }

    override fun getSecret(): String {
        TODO("Not yet implemented")
    }

    override fun getClaims(data: String): Claims {
        TODO("Not yet implemented")
    }

    override fun getData(claims: Claims): String {
        TODO("Not yet implemented")
    }
}
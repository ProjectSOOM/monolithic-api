package com.soom.monolithic_api.domain.account.auth.util

import com.soom.monolithic_api.domain.account.auth.property.EmailProperty
import com.soom.monolithic_api.global.token.jwt.util.StandardJwtUtil
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class JwtEmailTokenUtil(
    emailProperty: EmailProperty
): EmailTokenUtil, StandardJwtUtil<String>() {
    private val emailJwtProperty = emailProperty.jwt

    override fun getExpiredAt(now: LocalDateTime): LocalDateTime = emailJwtProperty.expiredSecond.let { now.plusSeconds(it) }
    override fun getSecret(): String = emailJwtProperty.secret
    override fun getClaims(data: String): Map<String, Any> = mapOf("email" to data)
    override fun getData(claims: Claims): String = claims.get("email", String::class.java)
}
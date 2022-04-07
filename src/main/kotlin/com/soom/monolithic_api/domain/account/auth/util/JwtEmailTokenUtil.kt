package com.soom.monolithic_api.domain.account.auth.util

import com.soom.monolithic_api.domain.account.auth.property.EmailTokenJwtProperty
import com.soom.monolithic_api.global.token.jwt.util.StandardJwtUtil
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class JwtEmailTokenUtil(
    val emailTokenJwtProperty: EmailTokenJwtProperty
): EmailTokenUtil, StandardJwtUtil<String>() {
    override fun getExpiredAt(now: LocalDateTime): LocalDateTime = emailTokenJwtProperty.expiredSecond.let { now.plusSeconds(it) }
    override fun getSecret(): String = emailTokenJwtProperty.secret
    override fun getClaims(data: String): Map<String, Any> = mapOf("email" to data)
    override fun getData(claims: Claims): String = claims.get("email", String::class.java)
}
package com.soom.monolithic_api.global.token.jwt.util

import com.soom.monolithic_api.global.token.jwt.exception.WrongJwtTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.sql.Timestamp
import java.time.LocalDateTime

abstract class StandardJwtUtil<T>: JwtUtil<T> {
    override fun encode(payload: T): String = LocalDateTime.now()
        .let {
        Jwts.builder().apply {
            setClaims(getClaims(payload))
            signWith(SignatureAlgorithm.HS256, getSecret())
            setIssuedAt(Timestamp.valueOf(it))
            setExpiration(Timestamp.valueOf(getExpiredAt(it)))
        }.compact()
    }

    override fun decode(token: String): T =
        runCatching {
            Jwts.parser()
                .setSigningKey(getSecret())
                .parseClaimsJws(token)
                .body.run(::getData)
        }.onFailure { throw WrongJwtTokenException(token) }
            .getOrThrow()

    protected abstract fun getExpiredAt(now: LocalDateTime): LocalDateTime
    protected abstract fun getSecret(): String
    protected abstract fun getClaims(data: T): Claims
    protected abstract fun getData(claims: Claims): T
}
package com.soom.monolithic_api.global.token.jwt.exception

class WrongJwtTokenException(token: String) : RuntimeException(message(token))

private fun message(token: String) = "잘못된 토큰입니다! - $token"

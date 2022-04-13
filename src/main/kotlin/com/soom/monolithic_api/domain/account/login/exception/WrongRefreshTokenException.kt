package com.soom.monolithic_api.domain.account.login.exception

class WrongRefreshTokenException(refreshToken: String) : RuntimeException(message(refreshToken))

private fun message(refreshToken: String) = "잘못된 재발급 토큰입니다! - $refreshToken"

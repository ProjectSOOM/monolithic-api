package com.soom.monolithic_api.domain.account.auth.exception

class UnknownAuthDataCodeException(code: String) : RuntimeException(message(code))
    private fun message(code: String) = "해당 코드에 해당하는 인증정보를 찾을 수 없습니다! - $code"

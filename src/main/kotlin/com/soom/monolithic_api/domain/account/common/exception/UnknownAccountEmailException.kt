package com.soom.monolithic_api.domain.account.common.exception

class UnknownAccountEmailException(val email: String) : RuntimeException(message(email))
    private fun message(email: String) = "해당 이메일을 가진 계정을 찾을 수 없습니다! - $email"


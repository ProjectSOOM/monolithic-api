package com.soom.monolithic_api.domain.account.login.exception

class WrongEmailException(email: String) : RuntimeException(message(email))

private fun message(email: String) = "잘못된 이메일입니다! - $email"

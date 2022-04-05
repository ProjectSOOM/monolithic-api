package com.soom.monolithic_api.domain.account.login.exception

class WrongPasswordException(password: String) : RuntimeException(message(password))

private fun message(password: String) = "잘못된 비밀번호입니다! - $password"
package com.soom.monolithic_api.domain.account.signup.exception

class WrongSchoolEmailException(val email: String): RuntimeException(message(email))

private fun message(email: String): String = "해당 이메일은 학교이메일이 아닙니다! - $email"

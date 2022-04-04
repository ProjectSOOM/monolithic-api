package com.soom.monolithic_api.domain.account.signup.dto

sealed class SignupDto(
    open val auth: SigninCommonAuthDto,
    open val profile: SigninCommonProfileDto
)
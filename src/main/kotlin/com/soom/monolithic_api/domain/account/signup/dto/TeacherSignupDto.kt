package com.soom.monolithic_api.domain.account.signup.dto

data class TeacherSignupDto (
    val auth: SigninCommonAuthDto,
    val profile: SigninCommonProfileDto,
    val teacher: SigninTeacherAdditionalDto
        )
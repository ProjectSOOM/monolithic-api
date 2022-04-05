package com.soom.monolithic_api.domain.account.signup.data.dto

data class TeacherSignupDto (
    override val auth: SigninCommonAuthDto,
    override val profile: SigninCommonProfileDto,
    val teacher: SigninTeacherAdditionalDto
        ): SignupDto(auth, profile)
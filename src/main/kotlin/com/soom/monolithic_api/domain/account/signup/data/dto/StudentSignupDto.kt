package com.soom.monolithic_api.domain.account.signup.data.dto

data class StudentSignupDto (
    override val auth: SigninCommonAuthDto,
    override val profile: SigninCommonProfileDto,
    val student: SigninStudentAdditionalDto
        ): SignupDto(auth, profile)
package com.soom.monolithic_api.domain.account.signup.dto

data class StudentSignupDto (
        val auth: SigninCommonAuthDto,
        val profile: SigninCommonProfileDto,
        val student: SigninStudentAdditionalDto
        )
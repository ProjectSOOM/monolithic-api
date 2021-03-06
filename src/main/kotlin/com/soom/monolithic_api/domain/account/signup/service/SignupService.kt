package com.soom.monolithic_api.domain.account.signup.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.signup.data.dto.StudentSignupDto
import com.soom.monolithic_api.domain.account.signup.data.dto.TeacherSignupDto

interface SignupService {
    fun signupStudent(dto: StudentSignupDto): AccountDto
    fun signupTeacher(dto: TeacherSignupDto): AccountDto
}

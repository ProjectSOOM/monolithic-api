package com.soom.monolithic_api.domain.account.signup.data.dto

import com.soom.monolithic_api.domain.account.common.data.type.DepartmentType
import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import com.soom.monolithic_api.domain.account.common.data.type.TeacherType
import java.time.LocalDate

interface SignupComponents
interface SignupAdditionalComponents: SignupComponents

data class SigninCommonAuthDto(
    val email: String,
    val password: String
): SignupComponents

data class SigninCommonProfileDto(
    val name: String,
    val gender: GenderType,
    val birth: LocalDate
): SignupComponents

data class SigninTeacherAdditionalDto(
    val major: String,
    val teacherType: TeacherType
): SignupAdditionalComponents

data class SigninStudentAdditionalDto(
    val classNumber: Int,
    val admissionAt: LocalDate,
    val department: DepartmentType
): SignupAdditionalComponents
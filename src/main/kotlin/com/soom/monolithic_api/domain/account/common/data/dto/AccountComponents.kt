package com.soom.monolithic_api.domain.account.common.data.dto

import com.soom.monolithic_api.domain.account.common.data.type.*
import java.time.LocalDate
import java.time.LocalDateTime

//계정정보를 구성하는 컴포넌트가 구현해야하는 인터페이스
sealed interface AccountComponents

//계정 기본정보
data class AccountBasicDto(
    val name: String,
    val gender: GenderType,
    val birth: LocalDate,
    val profileImage: String?,
    val school: SchoolType
): AccountComponents
//계정 인증정보
data class AccountAuthDto(
    val email: String,
    val encodedPassword: String
): AccountComponents
//계정 메타정보
data class AccountMetaDto(
    val id: Long,
    val type: AccountType,
    val createdAt: LocalDateTime
): AccountComponents

//교사 추가정보
data class TeacherAdditionalDto(
    val major: String,
    val teacherType: TeacherType
): AccountComponents
//학생 추가정보
data class StudentAdditionalDto(
    val classNumber: Int,
    val admissionAt: LocalDate,
    val department: DepartmentType
): AccountComponents
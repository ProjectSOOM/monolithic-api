package com.soom.monolithic_api.domain.account.dto

sealed class AccountProfileDto(
    val basic: AccountBasicDto,
    val auth: AccountAuthDto,
    val meta: AccountMetaDto
)

class TeacherProfileDto(
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val teacher: TeacherDto
): AccountProfileDto(basic, auth, meta)
class StudentProfileDto(
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val student: StudentDto
): AccountProfileDto(basic, auth, meta)
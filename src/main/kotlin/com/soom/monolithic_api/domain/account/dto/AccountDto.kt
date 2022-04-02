package com.soom.monolithic_api.domain.account.dto

sealed class AccountDto(
    val basic: AccountBasicDto,
    val auth: AccountAuthDto,
    val meta: AccountMetaDto
)

class TeacherProfileDto(
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val teacher: TeacherDto
): AccountDto(basic, auth, meta)
class StudentProfileDto(
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val student: StudentDto
): AccountDto(basic, auth, meta)
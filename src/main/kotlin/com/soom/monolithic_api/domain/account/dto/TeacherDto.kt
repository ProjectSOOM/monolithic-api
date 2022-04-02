package com.soom.monolithic_api.domain.account.dto

class TeacherDto (
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val teacher: TeacherAdditionalDto
): AccountDto(basic, auth, meta)
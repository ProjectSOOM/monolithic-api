package com.soom.monolithic_api.domain.account.common.data.dto

class TeacherDto (
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val teacher: TeacherAdditionalDto
): AccountDto(basic, auth, meta)
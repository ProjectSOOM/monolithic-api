package com.soom.monolithic_api.domain.account.dto

class StudentDto(
    basic: AccountBasicDto, auth: AccountAuthDto, meta: AccountMetaDto,
    val student: StudentAdditionalDto
): AccountDto(basic, auth, meta)
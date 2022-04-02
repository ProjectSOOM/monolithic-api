package com.soom.monolithic_api.domain.account.dto

sealed class AccountDto(
    val basic: AccountBasicDto,
    val auth: AccountAuthDto,
    val meta: AccountMetaDto
)
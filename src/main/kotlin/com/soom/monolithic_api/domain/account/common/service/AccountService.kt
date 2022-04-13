package com.soom.monolithic_api.domain.account.common.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto

interface AccountService {
    fun getAccount(id: Long): AccountDto
}
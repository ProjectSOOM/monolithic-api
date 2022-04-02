package com.soom.monolithic_api.global.service

import com.soom.monolithic_api.domain.account.dto.AccountDto

interface LoginAccountService {
    fun getLoginAccount(): AccountDto
}
package com.soom.monolithic_api.global.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto

interface LoginAccountService {
    fun getLoginAccount(): AccountDto
}
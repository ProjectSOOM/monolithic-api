package com.soom.monolithic_api.domain.account.service

import com.soom.monolithic_api.domain.account.dto.AccountDto

interface AccountProfileService {
    fun getProfile(id: String): AccountDto
}
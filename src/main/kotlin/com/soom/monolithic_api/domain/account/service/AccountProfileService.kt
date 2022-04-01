package com.soom.monolithic_api.domain.account.service

import com.soom.monolithic_api.domain.account.dto.AccountProfileDto

interface AccountProfileService {
    fun getProfile(id: String): AccountProfileDto
}
package com.soom.monolithic_api.domain.account.login.service

import com.soom.monolithic_api.domain.account.login.dto.LoginTokenDto

interface LoginService {
    fun login(email: String, password: String): LoginTokenDto
    fun login(refreshToken: String): LoginTokenDto
}

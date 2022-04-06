package com.soom.monolithic_api.domain.account.login.service

import org.springframework.stereotype.Service

@Service //TODO 추후 실제 Service 로 전환
class DummyLoginTokenService : LoginTokenService {
    override fun accessToken(id: Long): String = "accessToken"
    override fun refreshToken(id: Long): String = "refreshToken"
    override fun decode(token: String): Long = 0
}
package com.soom.monolithic_api.domain.account.auth.service

import org.springframework.stereotype.Service

@Service
class SimpleNumberAuthCodeService: AuthCodeService {
    //인증코드를 생성한다
    override fun generateAuthCode(): String =
        (0..999999).random()
            .let { String.format("%06d", it) }
}
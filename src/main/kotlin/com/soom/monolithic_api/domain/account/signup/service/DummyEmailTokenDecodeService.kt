package com.soom.monolithic_api.domain.account.signup.service

import org.springframework.stereotype.Service

@Service
class DummyEmailTokenDecodeService: EmailTokenDecodeService {
    override fun decode(emailToken: String): String = "s20072@gsm.hs.kr"
}
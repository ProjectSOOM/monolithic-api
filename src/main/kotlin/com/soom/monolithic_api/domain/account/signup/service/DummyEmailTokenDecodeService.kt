package com.soom.monolithic_api.domain.account.signup.service

import org.springframework.stereotype.Service

@Service //TODO 추후 실제 Service 로 전환
class DummyEmailTokenDecodeService: EmailTokenDecodeService {
    override fun decode(emailToken: String): String = "s20072@gsm.hs.kr"
}
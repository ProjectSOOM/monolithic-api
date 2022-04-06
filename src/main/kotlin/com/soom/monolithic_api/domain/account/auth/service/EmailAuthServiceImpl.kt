package com.soom.monolithic_api.domain.account.auth.service

import com.soom.monolithic_api.domain.account.auth.entity.EmailAuthCodeEntity
import com.soom.monolithic_api.domain.account.auth.exception.UnknownAuthDataCodeException
import com.soom.monolithic_api.domain.account.auth.repository.EmailAuthCodeRepository
import com.soom.monolithic_api.global.service.EmailSenderService
import org.springframework.stereotype.Service

@Service
class EmailAuthServiceImpl(
    private val emailSenderService: EmailSenderService,
    private val emailAuthCodeRepository: EmailAuthCodeRepository
): EmailAuthService {
    //인증정보를 저장한다
    override fun addAuthData(code: String, email: String): Unit =
        EmailAuthCodeEntity(code, email)
            .let { emailAuthCodeRepository.save(it) }
    //이메일로 인증코드를 보낸다
    override fun sendAuthCodeToEmail(code: String, email: String) =
        mapOf<String, String>("code" to code)
            .let { emailSenderService.sendEmail(email, getTitle(), getTemplatePath(), it) }
    //인증코드로 이메일을 가져온다
    override fun getEmailByAuthCode(code: String): String =
        emailAuthCodeRepository.findById(code) //코드로 인증정보를 가져온다
            .orElse(null) //인증정보를 찾지 못하면 null을 반환한다
            ?.apply { emailAuthCodeRepository.deleteById(code) } //인증정보가 존재할 경우, 인증정보를 삭제한다
            ?.email //인증정보가 존재할 경우, 이메일을 반환한다
            ?: throw UnknownAuthDataCodeException(code) //인증정보가 존재하지 않을 경우, 예외를 발생시킨다

    //TODO 나중에 ConfigurationProperties로 빼내기
    private fun getTitle(): String = "[SOOM] 인증번호가 도착했어요!"
    private fun getTemplatePath(): String = "email/auth/auth_code.html"
}
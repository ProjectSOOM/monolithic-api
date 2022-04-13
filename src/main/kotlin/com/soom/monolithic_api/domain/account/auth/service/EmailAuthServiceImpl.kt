package com.soom.monolithic_api.domain.account.auth.service

import com.soom.monolithic_api.domain.account.auth.data.entity.EmailAuthCodeEntity
import com.soom.monolithic_api.domain.account.auth.exception.UnknownAuthDataCodeException
import com.soom.monolithic_api.domain.account.auth.property.EmailProperty
import com.soom.monolithic_api.domain.account.auth.repository.EmailAuthCodeRepository
import com.soom.monolithic_api.infra.email.service.EmailSenderService
import org.springframework.stereotype.Service

@Service
class EmailAuthServiceImpl(
    private val emailSenderService: EmailSenderService,
    private val emailAuthCodeRepository: EmailAuthCodeRepository,
    emailProperty: EmailProperty
): EmailAuthService {
    private val emailSmtpProperty = emailProperty.smtp
    //인증정보를 저장한다
    override fun addAuthData(code: String, email: String): Unit =
        EmailAuthCodeEntity(code, email)
            .let { emailAuthCodeRepository.save(it) }
    //이메일로 인증코드를 보낸다
    override fun sendAuthCodeToEmail(code: String, email: String) =
        mapOf<String, String>("code" to code)
            .let { emailSenderService.sendEmail(email, emailSmtpProperty.title, emailSmtpProperty.templatePath, it) }
    //인증코드로 이메일을 가져온다
    override fun getEmailByAuthCode(code: String): String =
        emailAuthCodeRepository.findById(code) //코드로 인증정보를 가져온다
            .orElse(null) //인증정보를 찾지 못하면 null을 반환한다
            ?.apply { emailAuthCodeRepository.deleteById(code) } //인증정보가 존재할 경우, 인증정보를 삭제한다
            ?.email //인증정보가 존재할 경우, 이메일을 반환한다
            ?: throw UnknownAuthDataCodeException(code) //인증정보가 존재하지 않을 경우, 예외를 발생시킨다
}
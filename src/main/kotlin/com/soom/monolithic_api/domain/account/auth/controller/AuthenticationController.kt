package com.soom.monolithic_api.domain.account.auth.controller

import com.soom.monolithic_api.domain.account.auth.data.request.EmailRequest
import com.soom.monolithic_api.domain.account.auth.data.response.EmailTokenResponse
import com.soom.monolithic_api.domain.account.auth.data.response.TeacherTokenResponse
import com.soom.monolithic_api.domain.account.auth.service.EmailAuthService
import com.soom.monolithic_api.domain.account.auth.service.EmailTokenService
import com.soom.monolithic_api.domain.account.auth.service.TeacherTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v2/account/auth")
class AuthenticationController(
    private val emailAuthService: EmailAuthService,
    private val teacherTokenService: TeacherTokenService,
    private val emailTokenService: EmailTokenService
) {
    //인증코드를 메일로 송신
    @PostMapping
    fun sendAuthCodeToEmail(@RequestBody request: EmailRequest): ResponseEntity<Unit> = emailAuthService.run {
        generateAuthCode().let {
            addAuthData(it, request.email)
            sendAuthCodeToEmail(it, request.email)
        }
    }.run { ResponseEntity.ok().build() }
    //인증코드로 이메일토큰 조회
    @GetMapping("/email/{code}")
    fun getEmailTokenByCode(@PathVariable code: String): ResponseEntity<EmailTokenResponse> =
        emailAuthService.getEmailByAuthCode(code)
            .let { emailTokenService.generateEmailToken(it) }
            .let { EmailTokenResponse(it) }
            .let { ResponseEntity.ok(it) }
    //교사토큰 조회
    @GetMapping("/teacher")
    fun getTeacherCode(): ResponseEntity<TeacherTokenResponse> =
        teacherTokenService.generateTeacherToken()
            .let { TeacherTokenResponse(it) }
            .let { ResponseEntity.ok(it) }
}
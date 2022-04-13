package com.soom.monolithic_api.domain.account.auth.controller

import com.soom.monolithic_api.domain.account.auth.data.request.EmailRequest
import com.soom.monolithic_api.domain.account.auth.data.response.EmailTokenResponse
import com.soom.monolithic_api.domain.account.auth.data.response.TeacherTokenResponse
import com.soom.monolithic_api.domain.account.auth.service.AuthCodeService
import com.soom.monolithic_api.domain.account.auth.service.EmailAuthService
import com.soom.monolithic_api.domain.account.auth.util.EmailTokenUtil
import com.soom.monolithic_api.domain.account.auth.util.TeacherTokenUtil
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v2/account/auth")
class AuthenticationController(
    private val authCodeService: AuthCodeService,
    private val emailAuthService: EmailAuthService,
    private val teacherTokenUtil: TeacherTokenUtil,
    private val emailTokenUtil: EmailTokenUtil
) {
    //인증코드를 메일로 송신
    @PostMapping
    fun sendAuthCodeToEmail(@RequestBody request: EmailRequest): ResponseEntity<Unit> =
        authCodeService.generateAuthCode().let {
            emailAuthService.run {
                addAuthData(it, request.email)
                sendAuthCodeToEmail(it, request.email)
            }
        }.run { ResponseEntity.ok().build() }
    //인증코드로 이메일토큰 조회
    @GetMapping("/email/{code}")
    fun getEmailTokenByCode(@PathVariable code: String): ResponseEntity<EmailTokenResponse> =
        emailAuthService.getEmailByAuthCode(code)
            .let { emailTokenUtil.encode(it) }
            .let { EmailTokenResponse(it) }
            .let { ResponseEntity.ok(it) }
    //교사토큰 조회
    @GetMapping("/teacher")
    fun getTeacherCode(): ResponseEntity<TeacherTokenResponse> =
        teacherTokenUtil.encode()
            .let { TeacherTokenResponse(it) }
            .let { ResponseEntity.ok(it) }
}
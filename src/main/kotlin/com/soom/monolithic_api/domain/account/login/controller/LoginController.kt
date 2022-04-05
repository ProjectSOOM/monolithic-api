package com.soom.monolithic_api.domain.account.login.controller

import com.soom.monolithic_api.domain.account.login.request.LoginRefreshRequest
import com.soom.monolithic_api.domain.account.login.request.LoginRequest
import com.soom.monolithic_api.domain.account.login.response.LoginResponse
import com.soom.monolithic_api.domain.account.login.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/account/login")
class LoginController (
    private val loginService: LoginService
        ) {
    //계정 인증정보로 로그인 토큰 발급
    @PostMapping
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> =
        loginService.login(request.email, request.password)
            .run{ LoginResponse(accessToken, refreshToken) }
            .run { ResponseEntity.ok(this) }
    //재발급 토큰으로 로그인 토큰 발급
    @PostMapping("/refresh")
    fun refreshLogin(@RequestBody request: LoginRefreshRequest): ResponseEntity<LoginResponse> =
        loginService.login(request.refreshToken)
            .run { LoginResponse(accessToken, refreshToken) }
            .run { ResponseEntity.ok(this) }
}
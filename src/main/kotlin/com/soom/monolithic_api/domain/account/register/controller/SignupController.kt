package com.soom.monolithic_api.domain.account.register.controller

import com.soom.monolithic_api.domain.account.register.request.StudentSignupRequest
import com.soom.monolithic_api.domain.account.register.request.TeacherSignupRequest
import com.soom.monolithic_api.domain.account.register.service.SignupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/account/signup")
class SignupController(
    val signupService: SignupService
) {
    //학생 회원가입
    @PostMapping("/student")
    fun signupStudent(@RequestBody request: StudentSignupRequest): ResponseEntity<Any> {
        //TODO
    }
    //교사 회원가입
    @PostMapping("/teacher")
    fun signupTeacher(@RequestBody request: TeacherSignupRequest): ResponseEntity<Any> {
        //TODO
    }
}
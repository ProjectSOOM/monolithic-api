package com.soom.monolithic_api.domain.account.signup.controller

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.signup.dto.*
import com.soom.monolithic_api.domain.account.signup.request.StudentSignupRequest
import com.soom.monolithic_api.domain.account.signup.request.TeacherSignupRequest
import com.soom.monolithic_api.domain.account.signup.service.EmailTokenDecodeService
import com.soom.monolithic_api.domain.account.signup.service.SignupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/account/signup")
class SignupController(
    val signupService: SignupService,
    val emailTokenDecodeService: EmailTokenDecodeService
) {
    //학생 회원가입
    @PostMapping("/student")
    fun signupStudent(@RequestBody request: StudentSignupRequest): ResponseEntity<AccountDto> = requestToDto(request)
            .let { signupService.signupStudent(it) }
            .let { ResponseEntity.ok(it) }

    //교사 회원가입
    @PostMapping("/teacher")
    fun signupTeacher(@RequestBody request: TeacherSignupRequest): ResponseEntity<AccountDto> = requestToDto(request)
        .let { signupService.signupTeacher(it) }
        .let { ResponseEntity.ok(it) }

    private fun requestToDto(request: StudentSignupRequest): StudentSignupDto = (request to emailTokenDecodeService.decode(request.emailToken))
        .run {
            (SigninCommonAuthDto(second, first.password) to SigninCommonProfileDto(first.name, first.gender, first.birth)
                    to SigninStudentAdditionalDto(first.classNumber, first.admissionAt, first.department))
        }.run { StudentSignupDto(first.first, first.second, second) }

    private fun requestToDto(request: TeacherSignupRequest): TeacherSignupDto = (request to emailTokenDecodeService.decode(request.emailToken))
        .run {
            (SigninCommonAuthDto(second, first.password) to SigninCommonProfileDto(first.name, first.gender, first.birth)
                    to SigninTeacherAdditionalDto(first.major, first.teacherType))
        }.run { TeacherSignupDto(first.first, first.second, second) }
}
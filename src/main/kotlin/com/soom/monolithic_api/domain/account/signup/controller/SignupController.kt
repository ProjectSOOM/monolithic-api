package com.soom.monolithic_api.domain.account.signup.controller

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.signup.data.dto.*
import com.soom.monolithic_api.domain.account.signup.data.request.SignupRequest
import com.soom.monolithic_api.domain.account.signup.data.request.StudentSignupRequest
import com.soom.monolithic_api.domain.account.signup.data.request.TeacherSignupRequest
import com.soom.monolithic_api.domain.account.signup.service.EmailTokenDecodeService
import com.soom.monolithic_api.domain.account.signup.service.SignupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v2/account/signup")
class SignupController(
    val signupService: SignupService,
    val emailTokenDecodeService: EmailTokenDecodeService
    ) {
    //학생 회원가입
    @PostMapping("/student")
    fun signupStudent(@RequestBody @Valid request: StudentSignupRequest): ResponseEntity<AccountDto> = requestToDto(request)
            .let { signupService.signupStudent(it) }
            .let { ResponseEntity.ok(it) }
    //교사 회원가입
    @PostMapping("/teacher")
    fun signupTeacher(@RequestBody @Valid request: TeacherSignupRequest): ResponseEntity<AccountDto> = requestToDto(request)
        .let { signupService.signupTeacher(it) }
        .let { ResponseEntity.ok(it) }

    //학생 회원가입 요청을 DTO 로 치환한다.
    private fun requestToDto(request: StudentSignupRequest): StudentSignupDto = requestToDtoTemplate(
        {pair: Pair<StudentSignupRequest, String> -> SigninStudentAdditionalDto(pair.first.classNumber, pair.first.admissionAt, pair.first.department) },
        {auth, profile, student: SigninStudentAdditionalDto -> StudentSignupDto(auth, profile, student) }
    ).invoke(request)
    //교사 회원가입 요청을 DTO 로 치환한다.
    private fun requestToDto(request: TeacherSignupRequest): TeacherSignupDto = requestToDtoTemplate(
        {pair: Pair<TeacherSignupRequest, String> -> SigninTeacherAdditionalDto(pair.first.major, pair.first.teacherType) },
        {auth, profile, teacher: SigninTeacherAdditionalDto -> TeacherSignupDto(auth, profile, teacher) }
    ).invoke(request)
    //회원가입 요청을 DTO 로 치환하는 템플릿 함수
    private fun <T: SignupRequest, T2: SignupAdditionalComponents, R: SignupDto> requestToDtoTemplate(
        makeAdditionalDto: (Pair<T, String>) -> T2,
        makeDto: (SigninCommonAuthDto, SigninCommonProfileDto, T2) -> R): (T) -> R = {
            request -> (request to emailTokenDecodeService.decode(request.emailToken))
                .run { (SigninCommonAuthDto(second, first.password) to SigninCommonProfileDto(first.name, first.gender, first.birth) to makeAdditionalDto.invoke(this)) }
                .run { makeDto.invoke(first.first, first.second, second) }
        }
}
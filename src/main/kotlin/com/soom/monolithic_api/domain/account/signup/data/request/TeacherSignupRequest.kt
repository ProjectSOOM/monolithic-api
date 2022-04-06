package com.soom.monolithic_api.domain.account.signup.data.request

import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import com.soom.monolithic_api.domain.account.common.data.type.TeacherType
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past

data class TeacherSignupRequest(
    @NotBlank(message = "이메일 토큰을 입력해주세요!")
    override val emailToken: String,
    @NotBlank(message = "비밀번호를 입력해주세요!")
    override val password: String,
    @NotBlank(message = "이름을 입력해주세요!")
    override val name: String,
    @NotNull(message = "성별을 입력해주세요!")
    override val gender: GenderType,
    @NotNull(message = "생년월일을 입력해주세요!")
    @Past(message = "올바른 생년월일을 입력해주세요!")
    override val birth: LocalDate,
    @NotBlank(message = "담당분야를 입력해주세요!")
    val major: String,
    @NotNull(message = "교사유형을 입력해주세요!")
    val teacherType: TeacherType
): SignupRequest(emailToken, password, name, gender, birth)
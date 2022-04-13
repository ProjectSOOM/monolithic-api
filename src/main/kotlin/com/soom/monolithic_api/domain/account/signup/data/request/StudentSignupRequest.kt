package com.soom.monolithic_api.domain.account.signup.data.request

import com.soom.monolithic_api.domain.account.common.data.type.DepartmentType
import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past
import javax.validation.constraints.Pattern

data class StudentSignupRequest(
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
    @NotNull(message = "학번을 입력해주세요!")
    @Pattern(regexp = "[1-3][1-4][0-2][0-9]", message = "올바른 학번을 입력해주세요!")
    val classNumber: Int,
    @Past(message = "올바른 입학일을 입력해주세요!")
    val admissionAt: LocalDate,
    @NotNull(message = "학과를 입력해주세요!")
    val department: DepartmentType
    ): SignupRequest(emailToken, password, name, gender, birth)
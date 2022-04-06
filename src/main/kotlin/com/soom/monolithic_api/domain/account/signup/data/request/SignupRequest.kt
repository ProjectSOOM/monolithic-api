package com.soom.monolithic_api.domain.account.signup.data.request

import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

sealed class SignupRequest (
    @NotBlank(message = "이메일 토큰을 입력해주세요!")
    open val emailToken: String,
    @NotBlank(message = "비밀번호를 입력해주세요!")
    open val password: String,
    @NotBlank(message = "이름을 입력해주세요!")
    open val name: String,
    @NotBlank(message = "성별을 입력해주세요!")
    open val gender: GenderType,
    @NotBlank(message = "생년월일을 입력해주세요!")
    @Past(message = "올바른 생년월일을 입력해주세요!")
    open val birth: LocalDate
)
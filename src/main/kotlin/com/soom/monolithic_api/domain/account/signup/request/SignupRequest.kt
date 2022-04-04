package com.soom.monolithic_api.domain.account.signup.request

import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import java.time.LocalDate

sealed class SignupRequest (
    open val emailToken: String,
    open val password: String,
    open val name: String,
    open val gender: GenderType,
    open val birth: LocalDate
)
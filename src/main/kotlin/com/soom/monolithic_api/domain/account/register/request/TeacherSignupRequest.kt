package com.soom.monolithic_api.domain.account.register.request

import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import com.soom.monolithic_api.domain.account.common.data.type.TeacherType
import java.time.LocalDate

data class TeacherSignupRequest(
    override val emailToken: String,
    override val password: String,
    override val name: String,
    override val gender: GenderType,
    override val birth: LocalDate,
    val major: String,
    val teacherType: TeacherType
): SignupRequest(emailToken, password, name, gender, birth)
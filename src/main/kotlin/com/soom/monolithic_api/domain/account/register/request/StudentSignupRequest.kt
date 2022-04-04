package com.soom.monolithic_api.domain.account.register.request

import com.soom.monolithic_api.domain.account.common.data.type.DepartmentType
import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import java.time.LocalDate

data class StudentSignupRequest(
    override val emailToken: String,
    override val password: String,
    override val name: String,
    override val gender: GenderType,
    override val birth: LocalDate,
    val classNumber: Int,
    val admissionAt: LocalDate,
    val department: DepartmentType
    ): SignupRequest(emailToken, password, name, gender, birth)
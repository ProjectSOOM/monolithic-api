package com.soom.monolithic_api.domain.account.entity

import com.soom.monolithic_api.domain.account.type.DepartmentType
import com.soom.monolithic_api.domain.account.type.Gender
import com.soom.monolithic_api.domain.account.type.RoleType
import com.soom.monolithic_api.domain.account.type.SchoolType
import java.time.LocalDate
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("STUDENT")
class StudentEntity(
    id: Long, name: String, gender: Gender, birth: LocalDate, email: String, encodedPassword: String, role: RoleType, school: SchoolType,
    val classNumber: Int,
    val admissionAt: LocalDate,
    val department: DepartmentType
) : AccountEntity(id, email, name, gender, birth, encodedPassword, role, school)

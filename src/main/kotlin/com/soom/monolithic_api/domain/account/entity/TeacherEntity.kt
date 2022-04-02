package com.soom.monolithic_api.domain.account.entity

import com.soom.monolithic_api.domain.account.type.Gender
import com.soom.monolithic_api.domain.account.type.RoleType
import com.soom.monolithic_api.domain.account.type.SchoolType
import com.soom.monolithic_api.domain.account.type.TeacherType
import java.time.LocalDate
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@DiscriminatorValue("TEACHER")
class TeacherEntity(
        id: Long, name: String, gender: Gender, birth: LocalDate, email: String, encodedPassword: String, role: RoleType, school: SchoolType,
        val major: String,
        @Enumerated(EnumType.STRING)
        val teacherType: TeacherType
) : AccountEntity(id, email, name, gender, birth, encodedPassword, role, school)
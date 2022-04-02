package com.soom.monolithic_api.domain.account.entity

import com.soom.monolithic_api.domain.account.dto.*
import com.soom.monolithic_api.domain.account.type.*
import java.time.LocalDate
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@DiscriminatorValue("TEACHER")
class TeacherEntity(
        id: Long, name: String, gender: GenderType, birth: LocalDate, email: String, encodedPassword: String, role: RoleType, school: SchoolType, profileImage: String,
        val major: String,
        @Enumerated(EnumType.STRING)
        val teacherType: TeacherType
) : AccountEntity(id, email, name, gender, birth, encodedPassword, role, school, profileImage) {
        companion object {
                fun entityToDtoTeacher(): (TeacherEntity) -> AccountDto = TeacherEntity::toDto
        }
        override fun toDto(): TeacherDto = TeacherDto(
                AccountBasicDto(name, gender, birth, profileImage, school),
                AccountAuthDto(email, encodedPassword),
                AccountMetaDto(id, AccountType.교사, createdAt),
                TeacherAdditionalDto(major, teacherType)
        )
}
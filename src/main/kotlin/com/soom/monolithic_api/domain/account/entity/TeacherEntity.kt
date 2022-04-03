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
data class TeacherEntity(
        override val id: Long,
        override val name: String,
        @Enumerated(EnumType.STRING)
        override val gender: GenderType,
        override val birth: LocalDate,
        override val email: String,
        override val encodedPassword: String,
        @Enumerated(EnumType.STRING)
        override val role: RoleType,
        @Enumerated(EnumType.STRING)
        override val school: SchoolType,
        override val profileImage: String,
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
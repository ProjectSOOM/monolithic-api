package com.soom.monolithic_api.domain.account.common.data.entity

import com.soom.monolithic_api.domain.account.common.data.dto.*
import com.soom.monolithic_api.domain.account.common.data.type.*
import java.time.LocalDate
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@DiscriminatorValue("TEACHER")
data class TeacherEntity(
    override var id: Long,
    override var name: String,
    @Enumerated(EnumType.STRING)
        override var gender: GenderType,
    override var birth: LocalDate,
    override var email: String,
    override var encodedPassword: String,
    @Enumerated(EnumType.STRING)
        override var role: RoleType,
    @Enumerated(EnumType.STRING)
        override var school: SchoolType,
    override var profileImage: String,
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
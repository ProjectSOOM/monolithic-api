package com.soom.monolithic_api.domain.account.common.data.entity

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import com.soom.monolithic_api.domain.account.common.data.type.RoleType
import com.soom.monolithic_api.domain.account.common.data.type.SchoolType
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.JOINED)
sealed class AccountEntity(
    @Id
    val id: Long,
    val email: String,
    val name: String,
    val gender: GenderType,
    val birth: LocalDate,
    val encodedPassword: String,
    @Enumerated(EnumType.STRING)
    val role: RoleType,
    @Enumerated(EnumType.STRING)
    val school: SchoolType,
    val profileImage : String
) {
    abstract fun toDto(): AccountDto

    val createdAt: LocalDateTime = LocalDateTime.now()
}
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
    var id: Long,
    var email: String,
    var name: String,
    var gender: GenderType,
    var birth: LocalDate,
    var encodedPassword: String,
    @Enumerated(EnumType.STRING)
    var role: RoleType,
    @Enumerated(EnumType.STRING)
    var school: SchoolType,
    var profileImage : String
) {
    abstract fun toDto(): AccountDto

    val createdAt: LocalDateTime = LocalDateTime.now()
}
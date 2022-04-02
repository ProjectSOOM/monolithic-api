package com.soom.monolithic_api.domain.account.entity

import com.soom.monolithic_api.domain.account.type.Gender
import com.soom.monolithic_api.domain.account.type.RoleType
import com.soom.monolithic_api.domain.account.type.SchoolType
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DiscriminatorColumn(name = "DTYPE")
abstract class AccountEntity(
    @Id
    val id: Long,
    val email: String,
    val name: String,
    val gender: Gender,
    val birth: LocalDate,
    val encodedPassword: String,
    @Enumerated(EnumType.STRING)
    val role: RoleType,
    @Enumerated(EnumType.STRING)
    val school: SchoolType
) {
    val createdAt = LocalDateTime.now()
    val profileImage : String? = null
}
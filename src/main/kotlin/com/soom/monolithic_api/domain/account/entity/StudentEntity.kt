package com.soom.monolithic_api.domain.account.entity

import com.soom.monolithic_api.domain.account.dto.*
import com.soom.monolithic_api.domain.account.type.*
import java.time.LocalDate
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("STUDENT")
class StudentEntity(
    id: Long, name: String, gender: GenderType, birth: LocalDate, email: String, encodedPassword: String, role: RoleType, school: SchoolType,
    val classNumber: Int,
    val admissionAt: LocalDate,
    val department: DepartmentType
) : AccountEntity(id, email, name, gender, birth, encodedPassword, role, school) {
    override fun toDto(): AccountDto = StudentDto(
        AccountBasicDto(name, gender, birth, profileImage, school),
        AccountAuthDto(email, encodedPassword),
        AccountMetaDto(id, AccountType.교사, createdAt),
        StudentAdditionalDto(classNumber, admissionAt, department)
    )
}

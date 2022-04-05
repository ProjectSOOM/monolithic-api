package com.soom.monolithic_api.domain.account.common.repository

import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StudentRepository : JpaRepository<StudentEntity, Long> {
    fun findByEmail(email: String): Optional<StudentEntity>
}
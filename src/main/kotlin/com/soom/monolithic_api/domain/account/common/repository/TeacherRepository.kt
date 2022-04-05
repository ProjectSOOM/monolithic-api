package com.soom.monolithic_api.domain.account.common.repository

import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TeacherRepository : JpaRepository<TeacherEntity, Long> {
    fun findByEmail(email: String): Optional<TeacherEntity>
}

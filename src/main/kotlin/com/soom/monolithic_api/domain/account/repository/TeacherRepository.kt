package com.soom.monolithic_api.domain.account.repository

import com.soom.monolithic_api.domain.account.entity.TeacherEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository : JpaRepository<TeacherEntity, Long>

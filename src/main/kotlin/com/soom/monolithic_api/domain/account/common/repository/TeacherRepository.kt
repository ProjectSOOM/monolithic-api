package com.soom.monolithic_api.domain.account.common.repository

import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository : JpaRepository<TeacherEntity, Long>

package com.soom.monolithic_api.domain.account.common.repository

import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<StudentEntity, Long>


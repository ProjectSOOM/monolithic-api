package com.soom.monolithic_api.domain.account.repository

import com.soom.monolithic_api.domain.account.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<StudentEntity, Long>


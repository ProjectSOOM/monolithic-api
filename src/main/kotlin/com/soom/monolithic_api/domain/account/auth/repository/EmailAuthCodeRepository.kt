package com.soom.monolithic_api.domain.account.auth.repository

import com.soom.monolithic_api.domain.account.auth.data.entity.EmailAuthCodeEntity
import org.springframework.data.repository.CrudRepository

interface EmailAuthCodeRepository: CrudRepository<EmailAuthCodeEntity, String>
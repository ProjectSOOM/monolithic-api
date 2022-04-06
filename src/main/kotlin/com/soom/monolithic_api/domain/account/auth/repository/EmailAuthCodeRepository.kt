package com.soom.monolithic_api.domain.account.auth.repository

import com.soom.monolithic_api.domain.account.auth.entity.EmailAuthCodeEntity
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.repository.CrudRepository

@RedisHash("email-auth-code")
interface EmailAuthCodeRepository: CrudRepository<EmailAuthCodeEntity, String>
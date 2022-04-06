package com.soom.monolithic_api.domain.account.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("email-auth-code", timeToLive = 60 * 5)
class EmailAuthCodeEntity(@Id val authCode: String, val email: String)
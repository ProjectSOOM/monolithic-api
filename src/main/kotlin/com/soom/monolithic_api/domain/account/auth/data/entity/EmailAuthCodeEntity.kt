package com.soom.monolithic_api.domain.account.auth.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("email-auth-code", timeToLive = 60 * 5)
class EmailAuthCodeEntity(@Id val authCode: String, val email: String)
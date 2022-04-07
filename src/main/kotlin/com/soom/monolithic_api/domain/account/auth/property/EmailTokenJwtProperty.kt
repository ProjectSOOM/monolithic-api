package com.soom.monolithic_api.domain.account.auth.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("soom.email")
data class EmailTokenJwtProperty (
    val secret: String,
    val expiredSecond: Long
)
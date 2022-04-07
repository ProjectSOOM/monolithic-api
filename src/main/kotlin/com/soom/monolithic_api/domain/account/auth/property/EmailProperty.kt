package com.soom.monolithic_api.domain.account.auth.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("soom.email")
data class EmailProperty (
    val jwt: EmailJwtProperty,
    val smtp: EmailSmtpProperty
) {
    data class EmailJwtProperty (val secret: String, val expiredSecond: Long)
    data class EmailSmtpProperty (val title: String, val templatePath: String)
}
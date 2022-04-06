package com.soom.monolithic_api.domain.account.auth.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class EmailAuthCodeEntity(val email: String, @Id val authCode: String)
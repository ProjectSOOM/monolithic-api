package com.soom.monolithic_api.domain.account.repository

import com.soom.monolithic_api.domain.account.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<AccountEntity, Long>
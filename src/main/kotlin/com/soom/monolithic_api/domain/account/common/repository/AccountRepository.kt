package com.soom.monolithic_api.domain.account.common.repository

import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<AccountEntity, Long>
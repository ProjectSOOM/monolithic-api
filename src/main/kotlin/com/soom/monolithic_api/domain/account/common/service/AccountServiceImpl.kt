package com.soom.monolithic_api.domain.account.common.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity.Companion.entityToDtoStudent
import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity.Companion.entityToDtoTeacher
import com.soom.monolithic_api.domain.account.common.template.AccountTemplate
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val accountTemplate: AccountTemplate
) : AccountService {
    override fun getAccount(id: Long): AccountDto = accountTemplate.doAndGetWithAccountById(id, entityToDtoTeacher(), entityToDtoStudent())
}
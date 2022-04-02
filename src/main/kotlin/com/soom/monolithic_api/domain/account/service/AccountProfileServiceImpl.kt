package com.soom.monolithic_api.domain.account.service

import com.soom.monolithic_api.domain.account.dto.AccountDto
import com.soom.monolithic_api.domain.account.entity.StudentEntity.Companion.entityToDtoStudent
import com.soom.monolithic_api.domain.account.entity.TeacherEntity.Companion.entityToDtoTeacher
import com.soom.monolithic_api.domain.account.template.AccountTemplate
import org.springframework.stereotype.Service

@Service
class AccountProfileServiceImpl(
    private val accountTemplate: AccountTemplate
) : AccountProfileService {
    override fun getProfile(id: Long): AccountDto = accountTemplate.doAndGetWithAccountById(id, entityToDtoTeacher(), entityToDtoStudent())
}
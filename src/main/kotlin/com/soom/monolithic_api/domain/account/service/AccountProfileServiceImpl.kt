package com.soom.monolithic_api.domain.account.service

import com.soom.monolithic_api.domain.account.dto.AccountDto
import com.soom.monolithic_api.domain.account.exception.UnknownAccountException
import com.soom.monolithic_api.domain.account.repository.StudentRepository
import com.soom.monolithic_api.domain.account.repository.TeacherRepository
import org.springframework.stereotype.Service

@Service
class AccountProfileServiceImpl(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
) : AccountProfileService {
    override fun getProfile(id: Long): AccountDto =
        studentRepository.findById(id).orElse(null)?.toDto()
            ?: teacherRepository.findById(id).orElse(null)?.toDto()
            ?: throw UnknownAccountException(id)
}
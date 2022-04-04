package com.soom.monolithic_api.domain.account.common.template

import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.common.exception.UnknownAccountException
import com.soom.monolithic_api.domain.account.common.repository.StudentRepository
import com.soom.monolithic_api.domain.account.common.repository.TeacherRepository
import org.springframework.stereotype.Component

@Component
class RepositoryAccountTemplate(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
): AccountTemplate {
    override fun <T> doWithAccountById(id: Long, doWithAccount: (AccountEntity) -> T): T
            = doAndGetWithAccountById(id, doWithAccount, doWithAccount)

    override fun <T> doAndGetWithAccountById(id: Long, doWithAccount:  (AccountEntity) -> T): T
            = doAndGetWithAccountById(id, doWithAccount, doWithAccount)

    override fun doWithAccountById(
        id: Long,
        doWithTeacher: (TeacherEntity) -> Unit,
        doWithStudent: (StudentEntity) -> Unit
    ) = doAndGetWithAccountById(id, doWithTeacher, doWithStudent)

    override fun <T> doAndGetWithAccountById(
        id: Long,
        doWithTeacher:  (TeacherEntity) -> T,
        doWithStudent: (StudentEntity) -> T
    ): T = studentRepository.findById(id).orElse(null)?.run(doWithStudent)
        ?: teacherRepository.findById(id).orElse(null)?.run(doWithTeacher)
        ?: throw UnknownAccountException(id)
}
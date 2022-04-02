package com.soom.monolithic_api.domain.account.template

import com.soom.monolithic_api.domain.account.entity.StudentEntity
import com.soom.monolithic_api.domain.account.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.exception.UnknownAccountException
import com.soom.monolithic_api.domain.account.repository.StudentRepository
import com.soom.monolithic_api.domain.account.repository.TeacherRepository
import org.springframework.stereotype.Component

@Component
class RepositoryAccountTemplate(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
): AccountTemplate {
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
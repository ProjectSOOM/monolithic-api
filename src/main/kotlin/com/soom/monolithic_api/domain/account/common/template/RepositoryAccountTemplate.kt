package com.soom.monolithic_api.domain.account.common.template

import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.common.exception.UnknownAccountEmailException
import com.soom.monolithic_api.domain.account.common.exception.UnknownAccountIdException
import com.soom.monolithic_api.domain.account.common.repository.StudentRepository
import com.soom.monolithic_api.domain.account.common.repository.TeacherRepository
import org.springframework.stereotype.Component

@Component
class RepositoryAccountTemplate(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
): AccountTemplate {
    //email로 계정을 찾아서 콜백을 수행한다
    override fun <T> doWithAccountByEmail(email: String, doWithAccount: (AccountEntity) -> T): Unit = run { doAndGetWithAccountByEmail(email, doWithAccount) }
    //email로 계정을 찾아서 콜백을 수행하고, 반환값을 리턴한다
    override fun <T> doAndGetWithAccountByEmail(email: String, doWithAccount: (AccountEntity) -> T): T  =
        studentRepository.findByEmail(email).orElse(null)?.run(doWithAccount)
        ?: teacherRepository.findByEmail(email).orElse(null)?.run(doWithAccount)
        ?: throw UnknownAccountEmailException(email)

    //id로 계정을 찾아서 콜백을 수행한다
    override fun <T> doWithAccountById(id: Long, doWithAccount: (AccountEntity) -> T): Unit = run { doAndGetWithAccountById(id, doWithAccount) }
    //id로 계정을 찾아서 콜백을 수행하고, 반환값을 리턴한다
    override fun <T> doAndGetWithAccountById(id: Long, doWithAccount:  (AccountEntity) -> T): T
            = doAndGetWithAccountById(id, doWithAccount, doWithAccount)
    //id로 계정을 찾아서 해당 계정의 종류에 맞는 콜백을 수행한다
    override fun doWithAccountById(
        id: Long,
        doWithTeacher: (TeacherEntity) -> Unit,
        doWithStudent: (StudentEntity) -> Unit
    ) = doAndGetWithAccountById(id, doWithTeacher, doWithStudent)
    //id로 계정을 찾아서 해당 계정의 종류에 맞는 콜백을 수행하고, 반환값을 리턴한다
    override fun <T> doAndGetWithAccountById(id: Long,
        doWithTeacher:  (TeacherEntity) -> T,
        doWithStudent: (StudentEntity) -> T
    ): T = studentRepository.findById(id).orElse(null)?.run(doWithStudent)
        ?: teacherRepository.findById(id).orElse(null)?.run(doWithTeacher)
        ?: throw UnknownAccountIdException(id)
}
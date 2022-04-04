package com.soom.monolithic_api.domain.account.common.template

import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity

interface AccountTemplate {
    fun <T> doWithAccountById(
        id: Long,
        doWithAccount:  (AccountEntity) -> T
    ): T

    fun <T> doAndGetWithAccountById(
        id: Long,
        doWithAccount:  (AccountEntity) -> T
    ): T

    fun doWithAccountById(
        id: Long,
        doWithTeacher: (TeacherEntity) -> Unit, doWithStudent: (StudentEntity) -> Unit
    )

    fun <T> doAndGetWithAccountById(
        id: Long,
        doWithTeacher: (TeacherEntity) -> T, doWithStudent: (StudentEntity) -> T
    ): T
}
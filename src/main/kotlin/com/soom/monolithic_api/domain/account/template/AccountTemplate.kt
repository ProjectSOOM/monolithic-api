package com.soom.monolithic_api.domain.account.template

import com.soom.monolithic_api.domain.account.entity.StudentEntity
import com.soom.monolithic_api.domain.account.entity.TeacherEntity

interface AccountTemplate {
    fun doWithAccountById(
        id: Long,
        doWithTeacher: (TeacherEntity) -> Unit, doWithStudent: (StudentEntity) -> Unit
    )

    fun <T> doAndGetWithAccountById(
        id: Long,
        doWithTeacher: (TeacherEntity) -> T, doWithStudent: (StudentEntity) -> T
    ): T
}
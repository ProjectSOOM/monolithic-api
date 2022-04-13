package com.soom.monolithic_api.domain.account.auth.util

class DummyTeacherTokenUtil: TeacherTokenUtil {
    override fun encode(payload: Unit): String = "teacher-token"
    override fun decode(token: String) = Unit
}
package com.soom.monolithic_api.domain.account.auth.service

import org.springframework.stereotype.Service

@Service
class DummyTeacherTokenService: TeacherTokenService {
    override fun generateTeacherToken(): String = "teacher-token"
}
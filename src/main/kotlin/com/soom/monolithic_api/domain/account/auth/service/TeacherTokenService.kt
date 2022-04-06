package com.soom.monolithic_api.domain.account.auth.service

interface TeacherTokenService {
    fun generateTeacherToken(): String
}
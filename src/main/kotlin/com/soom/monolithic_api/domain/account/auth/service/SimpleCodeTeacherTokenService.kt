package com.soom.monolithic_api.domain.account.auth.service

import org.springframework.stereotype.Service

@Service
class SimpleCodeTeacherTokenService: TeacherTokenService {
    override fun generateTeacherToken(): String =
            (0..6)
                .map { (0..35).random() }
                .map { if(it < 10) it else 'A' + it - 10}
                .joinToString("")
}
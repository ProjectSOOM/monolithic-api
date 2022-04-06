package com.soom.monolithic_api.domain.account.auth.util

import org.springframework.stereotype.Service

@Service
class SimpleCodeTeacherTokenUtil: TeacherTokenUtil {
    override fun encode(payload: Unit): String =
            (0..6)
                .map { (0..35).random() }
                .map { if(it < 10) it else 'A' + it - 10}
                .joinToString("")

    override fun decode(token: String) = Unit
}
package com.soom.monolithic_api.domain.account.auth.util

import com.soom.monolithic_api.global.token.TokenUtil

interface TeacherTokenUtil: TokenUtil<Unit, String> {
    fun encode():String = encode(Unit)
    override fun encode(payload: Unit): String
    override fun decode(token: String)
}
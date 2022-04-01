package com.soom.monolithic_api.domain.account.response

import com.soom.monolithic_api.domain.account.type.AccountType

//프로필 식별 정보를 담은 응답
sealed class ProfileResponse (
    val id: String,
    val type: AccountType
)

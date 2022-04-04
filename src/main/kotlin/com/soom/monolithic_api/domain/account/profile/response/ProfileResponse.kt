package com.soom.monolithic_api.domain.account.profile.response

import com.soom.monolithic_api.domain.account.common.data.type.AccountType

//프로필 식별 정보를 담은 응답
sealed class ProfileResponse (
    val id: Long,
    val type: AccountType
)

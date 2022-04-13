package com.soom.monolithic_api.domain.account.common.exception

class UnknownAccountIdException(val id: Long) : RuntimeException(message(id))
private fun message(id: Long): String = "해당 아이디를 가진 계정을 찾을 수 없습니다! - $id"
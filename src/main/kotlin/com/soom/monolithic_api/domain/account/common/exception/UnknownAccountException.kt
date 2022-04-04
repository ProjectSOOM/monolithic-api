package com.soom.monolithic_api.domain.account.common.exception

class UnknownAccountException(val id: Long) : RuntimeException(message(id))

private fun message(id: Long): String = "아이디가${id}인 계정을 찾을 수 없습니다!"